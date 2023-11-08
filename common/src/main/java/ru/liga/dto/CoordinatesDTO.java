package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO для координат")
@Data
@AllArgsConstructor
public class CoordinatesDTO {

    @Schema(description = "Долгота")
    private Double longitude;

    @Schema(description = "Широта")
    private Double latitude;
}
