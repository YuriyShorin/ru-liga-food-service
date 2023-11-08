package ru.liga.deliveryservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import ru.liga.deliveryservice.dto.CourierIdDTO;
import ru.liga.deliveryservice.service.DeliveryService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

@Tag(name = "API для отправки заказов курьерам")
@RestController
@RequestMapping("api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    /**
     * Сервис для отправки заказов курьерам
     */
    private final DeliveryService deliveryService;

    @Operation(summary = "Получить все доставки")
    @GetMapping
    public ResponseEntity<?> getDeliveries(@RequestParam UUID courierId, @RequestParam @PositiveOrZero Integer pageIndex, @RequestParam @Positive Integer pageCount) {
        return deliveryService.getDeliveries(courierId, pageIndex, pageCount);
    }

    @Operation(summary = "Принять заказ")
    @PostMapping("/{id}/take")
    public ResponseEntity<?> take(@RequestBody CourierIdDTO courierIdDTO, @PathVariable UUID id) {
        return deliveryService.take(courierIdDTO, id);
    }


    @Operation(summary = "Завершить заказ")
    @PostMapping("/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable UUID id) {
        return deliveryService.complete(id);
    }
}
