package ru.liga.orderservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import ru.liga.orderservice.dto.CreateOrderRequestDTO;
import ru.liga.orderservice.dto.CreateOrderResponseDTO;
import ru.liga.dto.GetOrdersResponseDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.orderservice.service.OrderService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Tag(name = "API для оформления заказов")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    /**
     * Сервис для работы с заказами
     */
    private final OrderService orderService;

    @Operation(summary = "Создать новый заказ")
    @PostMapping
    public CreateOrderResponseDTO createOrder(@RequestBody @Valid CreateOrderRequestDTO createOrderResponseDTO) {
        return orderService.createOrder(createOrderResponseDTO);
    }

    @Operation(summary = "Получить все заказы")
    @GetMapping
    public GetOrdersResponseDTO getOrders() {
        return orderService.getOrders();
    }

    @Operation(summary = "Получить заказ по id")
    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable @Positive Long id) {
        return orderService.getOrderById(id);
    }
}
