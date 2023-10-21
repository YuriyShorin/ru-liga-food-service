package ru.liga.kitchenservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.liga.dto.GetOrdersResponseDTO;
import ru.liga.kitchenservice.service.KitchenService;

@Tag(name = "API для приема заказов на кухню")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class KitchenController {

    @Schema(description = "Сервис для KitchenController")
    private final KitchenService kitchenService;

    @Operation(summary = "Получить все заказы")
    @GetMapping
    public GetOrdersResponseDTO getOrders(@RequestParam("status") String status) {
        return kitchenService.getOrders(status);
    }
}
