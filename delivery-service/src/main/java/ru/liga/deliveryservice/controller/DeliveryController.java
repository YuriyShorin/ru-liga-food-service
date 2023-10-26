package ru.liga.deliveryservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.dto.ActionDTO;
import ru.liga.dto.GetDeliveriesResponseDTO;
import ru.liga.deliveryservice.service.DeliveryService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Tag(name = "API для отправки заказов курьерам")
@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    /**
     * Сервис для отправки заказов курьерам
     */
    private final DeliveryService deliveryService;

    @Operation(summary = "Создать доставку")
    @PostMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(@RequestBody ActionDTO actionDTO, @PathVariable @Positive Long id) {
        return deliveryService.createDelivery(id, actionDTO);
    }

    @Operation(summary = "Получить все доставки")
    @GetMapping
    public GetDeliveriesResponseDTO getDeliveries(@RequestParam("status") @NotNull String status) {
        return deliveryService.getDeliveries(status);
    }
}
