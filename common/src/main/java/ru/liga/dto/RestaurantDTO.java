package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(description = "DTO ресторана")
@Data
@AllArgsConstructor
public class RestaurantDTO {

    @Schema(description = "Название")
    @NotNull(message = "Название не может быть пустым")
    private String name;

    @Schema(description = "Адрес")
    @NotNull(message = "Название не может быть пустым")
    private String address;

    @Schema(description = "Стасус")
    @NotNull(message = "Название не может быть пустым")
    private String status;

    @Schema(description = "Coordinates")
    private CoordinatesDTO coordinates;
}
