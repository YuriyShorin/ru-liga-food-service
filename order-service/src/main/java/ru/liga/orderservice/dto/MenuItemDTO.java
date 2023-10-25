package ru.liga.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(description = "DTO меню")
@Data
@AllArgsConstructor
public class MenuItemDTO {

    @Schema(description = "Количество")
    @NotNull(message = "Количество не может быть пустым")
    private Integer quantity;

    @Schema(description = "Id товара в меню")
    @JsonProperty("menu_item_id")
    @NotNull(message = "Id товара в меню не может быть пустым")
    private Long menuItemId;
}
