package ru.liga.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * Модель заказа
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /**
     * Id
     */
    private Long id;

    /**
     * Id заказчика
     */
    private Long customerId;

    /**
     * Id ресторана
     */
    private Long restaurantId;

    /**
     * Статус
     */
    private String status;

    /**
     * Id курьера
     */
    private Long courierId;

    /**
     * Время заказа
     */
    private Timestamp timestamp;

    /**
     * Ресторан
     */
    private Restaurant restaurant;

    /**
     * Товары
     */
    private List<Item> items;

    public Order(Long id, Long customerId, Long restaurantId, String status, Long courierId, Timestamp timestamp) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.courierId = courierId;
        this.timestamp = timestamp;
    }

    public Order(Long customerId, Long restaurantId, String status,Timestamp timestamp) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.timestamp = timestamp;
    }
}
