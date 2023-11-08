package ru.liga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.enums.OrderStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

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
    private UUID id;

    /**
     * Id заказчика
     */
    private UUID customerId;

    /**
     * Id ресторана
     */
    private UUID restaurantId;

    /**
     * Статус
     */
    private OrderStatus status;

    /**
     * Id курьера
     */
    private UUID courierId;

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

    public Order(UUID customerId, UUID restaurantId, OrderStatus status, Timestamp timestamp) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.timestamp = timestamp;
    }
}
