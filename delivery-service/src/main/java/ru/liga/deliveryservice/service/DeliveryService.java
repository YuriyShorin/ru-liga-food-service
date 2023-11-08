package ru.liga.deliveryservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.liga.deliveryservice.dto.CustomerForDeliveryDTO;
import ru.liga.deliveryservice.dto.DeliveryDTO;
import ru.liga.deliveryservice.dto.GetDeliveriesResponseDTO;
import ru.liga.deliveryservice.dto.RestaurantForDeliveryDTO;
import ru.liga.deliveryservice.exception.DeliveryNotFoundException;
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
     * Получить все доставки по статусу
     */
    public ResponseEntity<?> getDeliveries(Integer pageIndex, Integer pageCount) {
        Pageable page = PageRequest.of(pageIndex / pageCount, pageCount);

        List<Order> orders = orderMapper.selectOrdersByStatus(OrderStatus.KITCHEN_FINISHED, page.getPageSize());
        List<DeliveryDTO> deliveryDTOS = new ArrayList<>();

        for (Order order : orders) {

            Restaurant restaurant = order.getRestaurant();
            Customer customer = order.getCustomer();
            Courier courier = order.getCourier();

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

    public ResponseEntity<?> take(UUID id) {
        Order order = orderMapper.selectOrderById(id);

        if (order == null) {
            throw new DeliveryNotFoundException();
        }

        order.setStatus(OrderStatus.DELIVERY_DELIVERING);
        orderMapper.updateOrder(order);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> complete(UUID id) {
        Order order = orderMapper.selectOrderById(id);

        if (order == null) {
            throw new DeliveryNotFoundException();
        }

        order.setStatus(OrderStatus.DELIVERY_COMPLETE);
        orderMapper.updateOrder(order);

        return ResponseEntity.ok().build();
    }
}
