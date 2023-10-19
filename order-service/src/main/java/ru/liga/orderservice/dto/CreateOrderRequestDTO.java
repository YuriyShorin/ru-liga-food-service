package ru.liga.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "DTO для запроса создания заказа")
@Data
public class CreateOrderRequestDTO {

    @Schema(description = "Id ресторана")
    @JsonProperty("restaurant_id")
    private Long restaurantId;

    @Schema(description = "Id заказчика")
    @JsonProperty("customer_id")
    private Long customerId;

    @Schema(description = "Список товаров в меню")
    @JsonProperty("menu_items")
    private List<MenuItemDTO> menuItems;
}
