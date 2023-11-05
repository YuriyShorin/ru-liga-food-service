package ru.liga.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.liga.dto.*;
import ru.liga.enums.OrderStatus;
import ru.liga.exception.OrderNotFoundException;
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
    public ResponseEntity<?> createOrder(CreateOrderRequestDTO createOrderResponseDTO) {
        Order order = new Order(createOrderResponseDTO.getCustomerId(), createOrderResponseDTO.getRestaurantId(), OrderStatus.CUSTOMER_CREATED.name(), Timestamp.from(Instant.now()));

        Long orderId = orderMapper.insertOrder(order).getId();
        List<Item> items = new ArrayList<>();
        for (MenuItemDTO menuItemDTO : createOrderResponseDTO.getMenuItems()) {
            RestaurantMenuItem restaurantMenuItem = orderMapper.selectRestaurantMenuItemById(menuItemDTO.getMenuItemId());
            Item item = new Item(orderId, menuItemDTO.getMenuItemId(), restaurantMenuItem.getPrice() * menuItemDTO.getQuantity(), menuItemDTO.getQuantity());
            items.add(item);
        }
        orderMapper.insertItems(items);

        return ResponseEntity.ok(new CreateOrderResponseDTO(orderId, "Secure url", Date.from(order.getTimestamp().toInstant().plusSeconds(3600))));
    }

    /**
     * Получить все заказы
     */
    public ResponseEntity<?> getOrders(Integer pageIndex, Integer pageCount) {
        Pageable page = PageRequest.of(pageIndex / pageCount, pageCount);

        List<Order> orders = orderMapper.selectOrders(page.getPageSize());
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

        return ResponseEntity.ok(new GetOrdersResponseDTO(orderDTOS, pageIndex, pageCount));
    }


    /**
     * Получить заказ по id
     */
    public ResponseEntity<?> getOrderById(Long id) {
        Order order = orderMapper.selectOrderById(id);

        if (order == null) {
           throw new OrderNotFoundException();
        }

        List<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item : order.getItems()) {
            itemDTOS.add(new ItemDTO(item.getRestaurantMenuItem().getPrice() * item.getQuantity(), item.getQuantity(), item.getRestaurantMenuItem().getName(), item.getRestaurantMenuItem().getImage()));
        }

        CoordinatesDTO restaurantCoordinates = new CoordinatesDTO(order.getRestaurant().getLongitude(), order.getRestaurant().getLatitude());
        RestaurantDTO restaurantDTO = new RestaurantDTO(order.getRestaurant().getName(), order.getRestaurant().getAddress(), order.getRestaurant().getStatus(), restaurantCoordinates);

        return ResponseEntity.ok(new OrderDTO(order.getId(), restaurantDTO, order.getTimestamp(), itemDTOS));
    }
}
