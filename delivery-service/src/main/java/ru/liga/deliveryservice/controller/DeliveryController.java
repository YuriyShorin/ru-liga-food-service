package ru.liga.deliveryservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.deliveryservice.dto.DeliveryCreateDTO;
import ru.liga.deliveryservice.dto.GetDeliveriesResponseDTO;
import ru.liga.deliveryservice.service.DeliveryService;

@Tag(name = "API для отправки заказов курьерам")
@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    @Schema(description = "Сервис для отправки заказов курьерам")
    private final DeliveryService deliveryService;

    @Operation(summary = "Создать доставку")
    @PostMapping("/{id}")
    public ResponseEntity<?> createDelivery(@RequestBody DeliveryCreateDTO deliveryCreateDTO, @PathVariable Long id) {
        return deliveryService.createDelivery(id, deliveryCreateDTO);
    }

    @Operation(summary = "Получить все доставки")
    @GetMapping
    public GetDeliveriesResponseDTO getDeliveries(@RequestParam("status") String status) {
        return deliveryService.getDeliveries(status);
    }
}
