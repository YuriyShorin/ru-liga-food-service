package ru.liga.model;

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
     * Заказчик
     */
    private Customer customer;

    /**
     * Курьер
     */
    private Courier courier;

    /**
     * Товары
     */
    private List<Item> items;

    public Order(Long customerId, Long restaurantId, String status,Timestamp timestamp) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.timestamp = timestamp;
    }
}
