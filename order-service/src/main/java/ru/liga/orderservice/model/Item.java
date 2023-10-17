package ru.liga.orderservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Модель товара")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "Id заказа")
    private Long orderId;

    @Schema(description = "Id товара в меню ресторана")
    private Long restaurantMenuItemId;

    @Schema(description = "Цена")
    private Double price;

    @Schema(description = "Количество")
    private Integer quantity;

    @Schema(description = "Заказ")
    private Order order;

    @Schema(description = "Товар в меню")
    private RestaurantMenuItem restaurantMenuItem;
}
