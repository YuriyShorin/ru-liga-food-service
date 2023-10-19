package ru.liga.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель товара в меню ресторана
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMenuItem {

    /**
     * Id
     */
    private Long id;

    /**
     * Id ресторана
     */
    private Long restaurantId;

    /**
     * Название
     */
    private String name;

    /**
     * Цена
     */
    private Double price;

    /**
     * Изображение
     */
    private String image;

    /**
     * Описание
     */
    private String description;

    /**
     * Ресторан
     */
    private Restaurant restaurant;

    /**
     * Товар
     */
    private Item item;
}
