package ru.liga.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.dto.MenuItemDTO;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Schema(description = "DTO для запроса создания заказа")
@Data
@AllArgsConstructor
public class CreateOrderRequestDTO {

    @Schema(description = "Id ресторана")
    @JsonProperty("restaurant_id")
    @NotNull(message = "Id ресторана не может быть пустым")
    private UUID restaurantId;

    @Schema(description = "Id заказчика")
    @JsonProperty("customer_id")
    @NotNull(message = "Id заказчика не может быть пустым")
    private UUID customerId;

    @Schema(description = "Список товаров в меню")
    @JsonProperty("menu_items")
    private List<MenuItemDTO> menuItems;
}
