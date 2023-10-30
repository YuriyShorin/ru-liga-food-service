package ru.liga.kitchenservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.liga.dto.*;
import ru.liga.kitchenservice.client.KitchenClient;
import ru.liga.kitchenservice.mapping.OrderMapper;
import ru.liga.model.Item;
import ru.liga.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для приема заказов на кухне
 */
@Service
@RequiredArgsConstructor
public class KitchenService {

    /**
     * Mapper для заказов
     */
    private final OrderMapper orderMapper;

    /**
     * Сервис для отправки сообщений RabbitMQ
     */
    private final RabbitMQProducerService rabbitMQProducerService;

    /**
     * Feign client для переадресации запросов на порт 8080
     */
    private final KitchenClient kitchenClient;

    /**
     * Получить все заказы
     */
    public GetOrdersResponseDTO getOrders(String status) {
        List<Order> orders = orderMapper.selectOrdersByStatus(status);
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders) {
            List<ItemDTO> itemDTOS = new ArrayList<>();

            for (Item item : order.getItems()) {
                itemDTOS.add(new ItemDTO(item.getRestaurantMenuItem().getPrice() * item.getQuantity(), item.getQuantity(), item.getRestaurantMenuItem().getName(), item.getRestaurantMenuItem().getImage()));
            }

            CoordinatesDTO restaurantCoordinates = new CoordinatesDTO(order.getRestaurant().getLongitude(), order.getRestaurant().getLatitude());
            RestaurantDTO restaurantDTO = new RestaurantDTO(order.getRestaurant().getName(), order.getRestaurant().getAddress(), order.getRestaurant().getStatus(), restaurantCoordinates);
            orderDTOS.add(new OrderDTO(order.getId(), restaurantDTO, order.getTimestamp(), itemDTOS));
        }

        return new GetOrdersResponseDTO(orderDTOS, 1, 10);
    }

    /**
     * Принять заказ
     */
    public ResponseEntity<?> acceptOrder(Long orderId, ActionDTO actionDTO) {
        return kitchenClient.updateOrderStatus(orderId, actionDTO);
    }

    /**
     * Отклонить заказ
     */
    public ResponseEntity<?> denyOrder(Long orderId, ActionDTO actionDTO) {
        return kitchenClient.updateOrderStatus(orderId, actionDTO);
    }

    /**
     * Завершить заказ
     */
    public void finishOrder(Long orderId, String routingKey) {
        rabbitMQProducerService.sendMessage(orderId.toString(), routingKey);
    }
}
