package ru.liga.deliveryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель курьера
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courier {

    /**
     * Id
     */
    private Long id;

    /**
     * Телефон
     */
    private String phone;

    /**
     * Статус
     */
    private String status;

    /**
     * Долгота
     */
    private Double longitude;

    /**
     * Широта
     */
    private Double latitude;

    /**
     * Оплата
     */
    private Double payment;
}
