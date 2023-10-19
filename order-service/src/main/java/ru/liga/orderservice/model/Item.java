package ru.liga.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;

    /**
     * Id заказа
     */
    private Long orderId;

    /**
     * Id товара в меню ресторана
     */
    private Long restaurantMenuItemId;

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

    public Item(Long orderId, Long restaurantMenuItemId, Double price, Integer quantity) {
        this.orderId = orderId;
        this.restaurantMenuItemId = restaurantMenuItemId;
        this.price = price;
        this.quantity = quantity;
    }
}
