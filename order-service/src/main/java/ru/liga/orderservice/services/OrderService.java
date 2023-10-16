package ru.liga.orderservice.services;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import ru.liga.orderservice.dto.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Schema(description = "Сервис для оформления заказов")
@Service
public class OrderService {

    @Operation(summary = "Получить все заказы")
    public GetOrdersResponseDTO getOrders() {
        return new GetOrdersResponseDTO(List.of(new OrderDTO(1L, new RestaurantDTO("Вкусно и точка"), Date.from(Instant.now()), List.of(new ItemDTO(315.0, 1, "Биг Спешиал комбо", "Картинка")))), 1, 10);
    }

    @Operation(summary = "Получить заказ по id")
    public OrderDTO getOrderById(@PathVariable Long id) {
        return new OrderDTO(1L, new RestaurantDTO("Вкусно и точка"), Date.from(Instant.now()), List.of(new ItemDTO(315.0, 1, "Биг Спешиал комбо", "Картинка")));
    }

    @Operation(summary = "Создать новый заказ")
    public CreateOrderResponseDTO createOrder(@RequestBody CreateOrderRequestDTO createOrderResponseDTO) {
        return new CreateOrderResponseDTO(1L, "https://someurl.com", Date.from(Instant.now().plusSeconds(3600)));
    }
}

