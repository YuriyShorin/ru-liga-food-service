package ru.liga.deliveryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO заказчика для доставки")
@Data
@AllArgsConstructor
public class CustomerForDeliveryDTO {

    @Schema(description = "Телефон")
    private String phone;

    @Schema(description = "Адрес")
    private String address;

    @Schema(description = "Расстояние")
    private Double distance;
}
