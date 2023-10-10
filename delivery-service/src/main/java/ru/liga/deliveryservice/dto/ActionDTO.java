package ru.liga.deliveryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "DTO действий курьера")
@Data
public class ActionDTO {

    @Schema(description = "Действие с заказом")
    @JsonProperty("order_action")
    private String orderAction;
}
