package ru.liga.kitchenservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.kitchenservice.service.KitchenService;

import java.util.UUID;

@Tag(name = "API для приема заказов на кухню")
@RestController
@RequestMapping("api/kitchen")
@RequiredArgsConstructor
public class KitchenController {

    /**
     * Сервис для приема заказов на кухне
     */
    private final KitchenService kitchenService;

    @Operation(summary = "Взять заказ")
    @PostMapping("/{id}/accept")
    public ResponseEntity<?> accept(@PathVariable UUID id) {
        return kitchenService.accept(id);
    }

    @Operation(summary = "Отклонить заказ")
    @PostMapping("/{id}/decline")
    public ResponseEntity<?> decline(@PathVariable UUID id) {
        return kitchenService.decline(id);
    }

    @Operation(summary = "Завершить заказ")
    @PostMapping("/{id}/ready")
    public ResponseEntity<?> ready(@PathVariable UUID id) {
        return kitchenService.ready(id);
    }
}
