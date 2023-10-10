package ru.liga.orderservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "Модель меню")
@Data
@AllArgsConstructor
public class MenuItems {

    @Schema(description = "Количество")
    private Integer quantity;

    @Schema(description = "Id товара в меню")
    @JsonProperty("menu_item_id")
    private Long menuItemId;
}
