package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(description = "DTO заказчика")
@Data
@AllArgsConstructor
public class CustomerDTO {

    @Schema(description = "Телефон")
    @NotNull(message = "Телефон не может быть пустым")
    private String phone;

    @Schema(description = "Электронная почта")
    @Email(message = "Некорректный email")
    private String email;

    @NotNull(message = "Адрес не может быть пустым")
    @Schema(description = "Адрес")
    private String address;

    @Schema(description = "Долгота")
    @Min(value = -180, message = "Долгота не может быть меньше -180 градусов")
    @Max(value = 180, message = "Долгота не может быть больше 180 градусов")
    private Double longitude;

    @Schema(description = "Широта")
    @Min(value = -90, message = "Широта не может быть меньше -90 градусов")
    @Max(value = 90, message = "Широта не может быть больше 90 градусов")
    private Double latitude;
}
