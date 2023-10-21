package ru.liga.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import ru.liga.dto.GetOrdersResponseDTO;
import ru.liga.dto.ItemDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.orderservice.dto.*;
import ru.liga.enums.OrderStatus;
import ru.liga.orderservice.exception.OrderNotFoundException;
import ru.liga.orderservice.mapping.OrderMapper;
import ru.liga.model.Item;
import ru.liga.model.Order;
import ru.liga.model.RestaurantMenuItem;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Сервис для оформления заказов
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    /**
     * Mapper для заказов
     */
    private final OrderMapper orderMapper;

    /**
     * Создать новый заказ
     */
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderResponseDTO) {
        Order order = new Order(createOrderResponseDTO.getCustomerId(), createOrderResponseDTO.getRestaurantId(), OrderStatus.CUSTOMER_CREATED.name(), Timestamp.from(Instant.now()));

        Long orderId = orderMapper.insertOrder(order).getId();
        List<Item> items = new ArrayList<>();
        for (MenuItemDTO menuItemDTO : createOrderResponseDTO.getMenuItems()) {
            RestaurantMenuItem restaurantMenuItem = orderMapper.selectRestaurantMenuItemById(menuItemDTO.getMenuItemId());
            Item item = new Item(orderId, menuItemDTO.getMenuItemId(), restaurantMenuItem.getPrice() * menuItemDTO.getQuantity(), menuItemDTO.getQuantity());
            items.add(item);
        }
        orderMapper.insertItems(items);

        return new CreateOrderResponseDTO(orderId, "Secure url", Date.from(order.getTimestamp().toInstant().plusSeconds(3600)));
    }

    /**
     * Получить все заказы
     */
    public GetOrdersResponseDTO getOrders() {
        List<Order> orders = orderMapper.selectOrders();
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


    /**
     * Получить заказ по id
     */
    public OrderDTO getOrderById(@PathVariable Long id) {
        Order order = orderMapper.selectOrderById(id);

        if (order == null) {
            throw new OrderNotFoundException();
        }

        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : order.getItems()) {
            itemDTOS.add(new ItemDTO(item.getRestaurantMenuItem().getPrice() * item.getQuantity(), item.getQuantity(), item.getRestaurantMenuItem().getName(), item.getRestaurantMenuItem().getImage()));
        }

        return new OrderDTO(order.getId(), new RestaurantDTO(order.getRestaurant().getName(), order.getRestaurant().getAddress(), order.getRestaurant().getStatus(), order.getRestaurant().getLongitude(), order.getRestaurant().getLatitude()), order.getTimestamp(), itemDTOS);
    }
}
