package ru.liga.deliveryservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.liga.deliveryservice.dto.*;
import ru.liga.deliveryservice.exception.DeliveryNotFoundException;
import ru.liga.deliveryservice.mapping.OrderMapper;
import ru.liga.dto.OrderActionDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.model.Customer;
import ru.liga.model.Item;
import ru.liga.model.Order;
import ru.liga.model.Restaurant;

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
            RestaurantDTO restaurantDTO = new RestaurantDTO(restaurant.getName(), restaurant.getAddress(), restaurant.getStatus(), restaurant.getLongitude(), restaurant.getLatitude());

            Customer customer = order.getCustomer();
            CustomerDTO customerDTO = new CustomerDTO(customer.getPhone(), customer.getAddress(), customer.getLongitude(), customer.getLatitude());

            double payment = 0.0;
            for (Item item : order.getItems()) {
                payment += item.getPrice() * item.getQuantity();
            }

            deliveryDTOS.add(new DeliveryDTO(order.getId(), restaurantDTO, customerDTO, payment));
        }

        return new GetDeliveriesResponseDTO(deliveryDTOS, 1, 10);
    }

    /**
     * Создать доставку
     */
    public ResponseEntity<?> createDelivery(Long id, OrderActionDTO orderActionDTO) {
        Order order = orderMapper.selectOrderById(id);

        if (order == null) {
            throw new DeliveryNotFoundException();
        }

        order.setStatus(orderActionDTO.getOrderAction());
        order.setCourierId(orderActionDTO.getCourierId());
        orderMapper.updateOrder(order);

        return ResponseEntity.ok().build();
    }
}
