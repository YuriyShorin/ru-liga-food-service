package ru.liga.kitchenservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.liga.kitchenservice.dto.OrdersDTO;
import ru.liga.kitchenservice.services.KitchenService;

import java.util.List;

@Tag(name = "API для приема заказов на кухню")
@RestController
@RequestMapping("/orders")
public class KitchenController {

    @Schema(description = "Сервис для KitchenController")
    private final KitchenService kitchenService;

    @Autowired
    public KitchenController(KitchenService kitchenController) {
        this.kitchenService = kitchenController;
    }

    @Operation(summary = "Получить все заказы")
    @GetMapping
    public List<OrdersDTO> getOrders(@RequestParam("status") String status) {
        return kitchenService.getOrders(status);
    }
}
