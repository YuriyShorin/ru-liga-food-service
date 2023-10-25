package ru.liga.kitchenservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.dto.OrderActionDTO;
import ru.liga.kitchenservice.client.KitchenClient;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Tag(name = "API доставки")
@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    /**
     * Feign client для переадресации запросов с порта 8080
     */
    private final KitchenClient kitchenClient;

    @Operation(summary = "Создать доставку")
    @PostMapping(value = "/{id}")
    ResponseEntity<?> createDelivery(@RequestBody @Valid OrderActionDTO orderActionDTO, @PathVariable @Positive Long id) {
        return kitchenClient.createDelivery(orderActionDTO, id);
    }
}
