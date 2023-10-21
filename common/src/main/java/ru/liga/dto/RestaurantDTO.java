package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO ресторана")
@Data
@AllArgsConstructor
public class RestaurantDTO {

    @Schema(description = "Имя")
    private String name;

    @Schema(description = "Адрес")
    private String address;

    @Schema(description = "Стасус")
    private String status;

    @Schema(description = "Долгота")
    private Double longitude;

    @Schema(description = "Широта")
    private Double latitude;
}
