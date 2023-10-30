package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
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

    @Schema(description = "Coordinates")
    private CoordinatesDTO coordinates;
}
