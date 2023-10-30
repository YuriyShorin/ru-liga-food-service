package ru.liga.deliveryservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.liga.dto.*;
import ru.liga.exception.DeliveryNotFoundException;
import ru.liga.deliveryservice.mapping.OrderMapper;
import ru.liga.model.*;
import ru.liga.dto.CoordinatesDTO;
import ru.liga.util.DistanceCalculator;
import ru.liga.util.PaymentCalculator;

import java.util.ArrayList;
import java.util.List;

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
    public GetDeliveriesResponseDTO getDeliveries(String status) {
        List<Order> orders = orderMapper.selectOrdersByStatus(status);
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

        return new GetDeliveriesResponseDTO(deliveryDTOS, 1, 10);
    }

    /**
     * Создать доставку
     */
    public ResponseEntity<?> createDelivery(Long id, ActionDTO actionDTO) {
        Order order = orderMapper.selectOrderById(id);

        if (order == null) {
            throw new DeliveryNotFoundException();
        }

        order.setStatus(actionDTO.getOrderAction());
        orderMapper.updateOrder(order);

        return ResponseEntity.ok().build();
    }
}
