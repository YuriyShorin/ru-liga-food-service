package ru.liga.deliveryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Schema(description = "DTO доставок")
@Data
@AllArgsConstructor
public class DeliveryDTO {

    @Schema(description = "Id заказа")
    @JsonProperty("order_id")
    private UUID orderId;

    @Schema(description = "Ресторан")
    private RestaurantForDeliveryDTO restaurant;

    @Schema(description = "Заказчик")
    private CustomerForDeliveryDTO customer;

    @Schema(description = "Оплата")
    private Double payment;
}
