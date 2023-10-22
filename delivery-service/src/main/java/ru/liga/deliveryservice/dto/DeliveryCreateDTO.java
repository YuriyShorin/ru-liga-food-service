package ru.liga.deliveryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "DTO создания доставки")
@Data
public class DeliveryCreateDTO {

    @Schema(description = "Id курьера")
    @JsonProperty("courier_id")
    private Long courierId;

    @Schema(description = "Действие с заказом")
    @JsonProperty("order_action")
    private String orderAction;
}
