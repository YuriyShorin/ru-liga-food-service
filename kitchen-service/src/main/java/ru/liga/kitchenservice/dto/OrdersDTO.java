package ru.liga.kitchenservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Schema(description = "DTO заказов")
@Data
@AllArgsConstructor
public class OrdersDTO {

    @Schema(description = "Id заказа")
    private Long id;

    @Schema(description = "Список товаров в меню")
    @JsonProperty("menu_items")
    private List<MenuItemDTO> menuItems;
}
