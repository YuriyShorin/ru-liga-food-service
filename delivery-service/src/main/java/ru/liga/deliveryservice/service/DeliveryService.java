package ru.liga.deliveryservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.liga.deliveryservice.dto.*;
import ru.liga.deliveryservice.exception.*;
import ru.liga.deliveryservice.mapping.CourierMapper;
import ru.liga.enums.CourierStatus;
import ru.liga.enums.OrderStatus;
import ru.liga.deliveryservice.mapping.OrderMapper;
import ru.liga.model.*;
import ru.liga.dto.CoordinatesDTO;
import ru.liga.deliveryservice.util.DistanceCalculator;
import ru.liga.deliveryservice.util.PaymentCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Сервис доставки
 */
@Service
@RequiredArgsConstructor
public class DeliveryService {

    /**
     * Mapper для заказов
     */
    private final OrderMapper orderMapper;

    /**
     * Mapper для курьеров
     */
    private final CourierMapper courierMapper;

    /**
     * Сервис для отправки сообщений RabbitMQ
     */
    private final RabbitMQProducerService rabbitMQProducerService;

    /**
     * Получить все доставки по статусу
     */
    @Transactional(readOnly = true)
    public ResponseEntity<?> getDeliveries(UUID courierId, Integer pageIndex, Integer pageCount) {
        Pageable page = PageRequest.of(pageIndex / pageCount, pageCount);

        List<Order> orders = orderMapper.selectOrdersByStatus(OrderStatus.KITCHEN_FINISHED, page.getPageSize());
        List<DeliveryDTO> deliveryDTOS = new ArrayList<>();

        Courier courier = courierMapper.selectCourierById(courierId);

        if (courier == null) {
            throw new CourierNotFoundException();
        }

        for (Order order : orders) {

            Restaurant restaurant = order.getRestaurant();
            Customer customer = order.getCustomer();

            CoordinatesDTO courierCoordinatesDTO = new CoordinatesDTO(courier.getLongitude(), courier.getLatitude());
            CoordinatesDTO restaurantCoordinatesDTO = new CoordinatesDTO(restaurant.getLongitude(), restaurant.getLatitude());
            CoordinatesDTO customerCoordinatesDTO = new CoordinatesDTO(customer.getLongitude(), customer.getLatitude());

            Double distanceBetweenCourierAndRestaurant = DistanceCalculator.calculate(courierCoordinatesDTO, restaurantCoordinatesDTO);
            Double distanceBetweenCourierAndCustomer = DistanceCalculator.calculate(courierCoordinatesDTO, customerCoordinatesDTO);

            RestaurantForDeliveryDTO restaurantForDeliveryDTO = new RestaurantForDeliveryDTO(restaurant.getName(), restaurant.getAddress(), distanceBetweenCourierAndRestaurant);
            CustomerForDeliveryDTO customerForDeliveryDTO = new CustomerForDeliveryDTO(customer.getPhone(), customer.getAddress(), distanceBetweenCourierAndCustomer);

            deliveryDTOS.add(new DeliveryDTO(order.getId(), restaurantForDeliveryDTO, customerForDeliveryDTO, PaymentCalculator.calculate(order.getItems())));
        }

        return ResponseEntity.ok(new GetDeliveriesResponseDTO(deliveryDTOS, pageIndex, pageCount));
    }

    @Transactional
    public ResponseEntity<?> take(CourierIdDTO courierIdDTO, UUID id) {
        Courier courier = courierMapper.selectCourierById(courierIdDTO.getCourierId());

        if (courier == null) {
            throw new CourierNotFoundException();
        }

        if (courier.getStatus() == CourierStatus.ACTIVE) {
            throw new CourierAlreadyActiveException();
        }

        Order order = orderMapper.selectOrderById(id);

        if (order == null) {
            throw new DeliveryNotFoundException();
        }

        if (order.getStatus() == OrderStatus.CUSTOMER_CANCELLED || order.getStatus() == OrderStatus.KITCHEN_DECLINED) {
            throw new OrderCanceledException();
        }

        if (order.getStatus() == OrderStatus.DELIVERY_DELIVERING) {
            throw new OrderAlreadyDeliveringException();
        }

        if (order.getStatus() == OrderStatus.DELIVERY_COMPLETE) {
            throw new DeliveryAlreadyCompleteException();
        }

        if (order.getStatus() != OrderStatus.KITCHEN_FINISHED) {
            throw new KitchenNotPreparedException();
        }

        courier.setStatus(CourierStatus.ACTIVE);
        courierMapper.updateCourier(courier);

        order.setStatus(OrderStatus.DELIVERY_DELIVERING);
        order.setCourierId(courier.getId());
        orderMapper.updateOrder(order);

        rabbitMQProducerService.sendMessage("Курьер начал доставку, id заказчика: " + order.getCustomerId() +
                " id курьера: " + courier.getId(), "notification");

        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<?> complete(UUID id) {
        Order order = orderMapper.selectOrderById(id);

        if (order == null) {
            throw new DeliveryNotFoundException();
        }

        if (order.getStatus() == OrderStatus.CUSTOMER_CANCELLED || order.getStatus() == OrderStatus.KITCHEN_DECLINED) {
            throw new OrderCanceledException();
        }

        if (order.getStatus() == OrderStatus.DELIVERY_COMPLETE) {
            throw new KitchenNotPreparedException();
        }

        if (order.getStatus() != OrderStatus.DELIVERY_DELIVERING) {
            throw new OrderNotSentForDeliveryException();
        }

        order.setStatus(OrderStatus.DELIVERY_COMPLETE);
        orderMapper.updateOrder(order);

        Courier courier = courierMapper.selectCourierById(order.getCourierId());
        courier.setStatus(CourierStatus.FREE);
        courierMapper.updateCourier(courier);

        rabbitMQProducerService.sendMessage("Заказ доставлен, id заказчика: " + order.getCustomerId() +
                " id курьера: " + courier.getId(), "notification");

        return ResponseEntity.ok().build();
    }
}
