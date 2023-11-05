package ru.liga.orderservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.dto.CreateOrderRequestDTO;
import ru.liga.orderservice.service.OrderService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

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
    public ResponseEntity<?> createOrder(@RequestBody @Valid CreateOrderRequestDTO createOrderResponseDTO) {
        return orderService.createOrder(createOrderResponseDTO);
    }

    @Operation(summary = "Получить все заказы")
    @GetMapping
    public ResponseEntity<?> getOrders(@PositiveOrZero @RequestParam Integer pageIndex, @Positive @RequestParam Integer pageCount) {
        return orderService.getOrders(pageIndex, pageCount);
    }

    @Operation(summary = "Получить заказ по id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable @Positive Long id) {
        return orderService.getOrderById(id);
    }
}
