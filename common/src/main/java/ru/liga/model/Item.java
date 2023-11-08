package ru.liga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Модель товара
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    /**
     * Id
     */
    private UUID id;

    /**
     * Id заказа
     */
    private UUID orderId;

    /**
     * Id товара в меню ресторана
     */
    private UUID restaurantMenuItemId;

    /**
     * Цена
     */
    private Double price;

    /**
     * Количество
     */
    private Integer quantity;

    /**
     * Заказ
     */
    private Order order;

    /**
     * Товар в меню
     */
    private RestaurantMenuItem restaurantMenuItem;

    public Item(UUID orderId, UUID restaurantMenuItemId, Double price, Integer quantity) {
        this.orderId = orderId;
        this.restaurantMenuItemId = restaurantMenuItemId;
        this.price = price;
        this.quantity = quantity;
    }
}
