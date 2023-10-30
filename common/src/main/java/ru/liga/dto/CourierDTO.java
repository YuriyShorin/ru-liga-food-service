package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(description = "DTO курьеров")
@Data
@AllArgsConstructor
public class CourierDTO {

    @Schema(description = "Телефон")
    @NotNull
    private String phone;

    @Schema(description = "Статус")
    @NotNull
    private String status;

    @Schema(description = "Coordinates")
    private CoordinatesDTO coordinates;
}