package ru.liga.kitchenservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.dto.RestaurantDTO;
import ru.liga.dto.GetRestaurantsResponseDTO;
import ru.liga.kitchenservice.service.RestaurantService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Tag(name = "API для работы с ресторанами")
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    /**
     * Сервис для работы с ресторанами
     */
    private final RestaurantService restaurantService;

    @Operation(summary = "Создать ресторан")
    @PostMapping
    public ResponseEntity<?> createRestaurant(@RequestBody @Valid RestaurantDTO restaurantDTO) {
        return restaurantService.createRestaurant(restaurantDTO);
    }

    @Operation(summary = "Получить все рестораны")
    @GetMapping
    public GetRestaurantsResponseDTO getRestaurants(@RequestParam @PositiveOrZero Integer pageIndex, @RequestParam @Positive Integer pageCount) {
        return restaurantService.getRestaurants(pageIndex, pageCount);
    }

    @Operation(summary = "Получить ресторан по id")
    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable @Positive Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @Operation(summary = "Изменить ресторан")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@RequestBody @Valid RestaurantDTO restaurantDTO, @PathVariable @Positive Long id) {
        return restaurantService.updateRestaurant(id, restaurantDTO);
    }
}
