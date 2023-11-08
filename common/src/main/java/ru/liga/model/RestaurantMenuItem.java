package ru.liga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
    private UUID id;

    /**
     * Id ресторана
     */
    private UUID restaurantId;

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
