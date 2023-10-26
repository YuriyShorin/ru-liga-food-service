package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(description = "DTO создания доставки")
@Data
public class ActionDTO {

    @Schema(description = "Действие с заказом")
    @JsonProperty("order_action")
    @NotNull(message = "Действие с заказом не может быть пустым")
    private String orderAction;
}
