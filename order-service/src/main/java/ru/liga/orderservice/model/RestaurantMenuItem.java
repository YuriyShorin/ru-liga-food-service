package ru.liga.orderservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Модель товара в меню ресторана")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMenuItem {

    @Schema(description = "Id")
    private Long id;

    @Schema(description = "Id ресторана")
    private Long restaurantId;

    @Schema(description = "Название")
    private String name;

    @Schema(description = "Цена")
    private Double price;

    @Schema(description = "Изображение")
    private String image;

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Ресторан")
    private Restaurant restaurant;

    @Schema(description = "Товар")
    private Item item;
}
