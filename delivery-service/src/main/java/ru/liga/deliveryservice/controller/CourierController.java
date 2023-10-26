package ru.liga.deliveryservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.deliveryservice.service.CourierService;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.GetCouriersResponseDTO;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Tag(name = "API для работы с курьерами")
@RestController
@RequestMapping("/courier")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @Operation(summary = "Создать курьера")
    @PostMapping
    public ResponseEntity<?> createCourier(@RequestBody @Valid CourierDTO courierDTO) {
        return courierService.createCourier(courierDTO);
    }

    @Operation(summary = "Получить всех курьеров")
    @GetMapping
    public GetCouriersResponseDTO getCouriers(@RequestParam @PositiveOrZero Integer pageIndex, @RequestParam @Positive Integer pageCount) {
        return courierService.getCouriers(pageIndex, pageCount);
    }

    @Operation(summary = "Получить курьера по id")
    @GetMapping("/{id}")
    public CourierDTO getCourier(@PathVariable Long id) {
        return courierService.getCourier(id);
    }

    @Operation(summary = "Изменить курьера по id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourier(@Valid @RequestBody CourierDTO courierDTO, @PathVariable @Positive Long id) {
        return courierService.updateCourier(id, courierDTO);
    }
}
