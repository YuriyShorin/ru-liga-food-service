package ru.liga.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Модель ресторана
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    /**
     * Id
     */
    private Long id;

    /**
     * Статус
     */
    private String status;

    /**
     * Адрес
     */
    private String address;

    /**
     * Заказы
     */
    private List<Order> orders;

    public Restaurant(Long id, String status, String address) {
        this.id = id;
        this.status = status;
        this.address = address;
    }
}
