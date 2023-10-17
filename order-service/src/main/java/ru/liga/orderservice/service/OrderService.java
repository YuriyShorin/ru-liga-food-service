package ru.liga.orderservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import ru.liga.orderservice.dto.*;
import ru.liga.orderservice.mapping.OrderMapper;
import ru.liga.orderservice.model.Item;
import ru.liga.orderservice.model.Order;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Schema(description = "Сервис для оформления заказов")
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    @Operation(summary = "Получить все заказы")
    public GetOrdersResponseDTO getOrders() {
        List<Order> orders = orderMapper.selectOrders();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders) {
            List<ItemDTO> itemDTOS = new ArrayList<>();
            for (Item item : order.getItems()) {
                itemDTOS.add(new ItemDTO(item.getRestaurantMenuItem().getPrice() * item.getQuantity(), item.getQuantity(), item.getRestaurantMenuItem().getName(), item.getRestaurantMenuItem().getImage()));
            }
            orderDTOS.add(new OrderDTO(order.getId(), new RestaurantDTO(order.getRestaurant().getAddress()), order.getTimestamp(), itemDTOS));
        }

        return new GetOrdersResponseDTO(orderDTOS, 1, 10);
    }

    @Operation(summary = "Получить заказ по id")
    public OrderDTO getOrderById(@PathVariable Long id) {
        Order order = orderMapper.selectOrderById(id);

        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : order.getItems()) {
            itemDTOS.add(new ItemDTO(item.getRestaurantMenuItem().getPrice() * item.getQuantity(), item.getQuantity(), item.getRestaurantMenuItem().getName(), item.getRestaurantMenuItem().getImage()));
        }

        return new OrderDTO(order.getId(), new RestaurantDTO(order.getRestaurant().getAddress()), order.getTimestamp(), itemDTOS);
    }

    @Operation(summary = "Создать новый заказ")
    public CreateOrderResponseDTO createOrder(@RequestBody CreateOrderRequestDTO createOrderResponseDTO) {
        return new CreateOrderResponseDTO(1L, "https://someurl.com", Date.from(Instant.now().plusSeconds(3600)));
    }
}

