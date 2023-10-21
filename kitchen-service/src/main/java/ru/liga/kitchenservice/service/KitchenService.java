package ru.liga.kitchenservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ru.liga.dto.GetOrdersResponseDTO;
import ru.liga.dto.ItemDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.RestaurantDTO;
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
            orderDTOS.add(new OrderDTO(order.getId(), new RestaurantDTO(order.getRestaurant().getName(), order.getRestaurant().getAddress(), order.getRestaurant().getStatus(), order.getRestaurant().getLongitude(), order.getRestaurant().getLatitude()), order.getTimestamp(), itemDTOS));
        }

        return new GetOrdersResponseDTO(orderDTOS, 1, 10);
    }
}
