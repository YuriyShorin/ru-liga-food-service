package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    @Schema(description = "Долгота")
    @Min(value = -180, message = "Долгота не может быть меньше -180 градусов")
    @Max(value = 180, message = "Долгота не может быть больше 180 градусов")
    private Double longitude;

    @Schema(description = "Широта")
    @Min(value = -90, message = "Широта не может быть меньше -90 градусов")
    @Max(value = 90, message = "Широта не может быть больше 90 градусов")
    private Double latitude;
}