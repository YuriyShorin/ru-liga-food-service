package ru.liga.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO заказчика")
@Data
@AllArgsConstructor
public class CustomerDTO {

    @Schema(description = "Телефон")
    private String phone;

    @Schema(description = "Электронная почта")
    private String email;

    @Schema(description = "Адрес")
    private String address;

    @Schema(description = "Долгота")
    private Double longitude;

    @Schema(description = "Широта")
    private Double latitude;
}
