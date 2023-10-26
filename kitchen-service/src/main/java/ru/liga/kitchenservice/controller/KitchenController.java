package ru.liga.kitchenservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.dto.ActionDTO;
import ru.liga.dto.GetOrdersResponseDTO;
import ru.liga.kitchenservice.service.KitchenService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Tag(name = "API для приема заказов на кухню")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class KitchenController {

    /**
     * Сервис для приема заказов на кухне
     */
    private final KitchenService kitchenService;

    @Operation(summary = "Получить все заказы")
    @GetMapping
    public GetOrdersResponseDTO getOrders(@RequestParam("status") @NotNull String status) {
        return kitchenService.getOrders(status);
    }

    @Operation(summary = "Взять заказ")
    @PostMapping("/accept/{orderId}")
    public ResponseEntity<?> acceptOrder(@PathVariable Long orderId, @RequestBody @Valid ActionDTO actionDTO) {
        return kitchenService.acceptOrder(orderId, actionDTO);
    }

    @Operation(summary = "Отклонить заказ")
    @PostMapping("/deny/{orderId}")
    public ResponseEntity<?> denyOrder(@PathVariable Long orderId, @RequestBody @Valid ActionDTO actionDTO) {
        return kitchenService.denyOrder(orderId, actionDTO);
    }

    @Operation(summary = "Завершить заказ")
    @PostMapping("/finish/{orderId}")
    public void finishOrder(@PathVariable Long orderId, @RequestParam(name = "routingKey") String routingKey) {
        kitchenService.finishOrder(orderId, routingKey);
    }
}
