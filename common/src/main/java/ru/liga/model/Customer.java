package ru.liga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Модель заказчика
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    /**
     * Id
     */
    private UUID id;

    /**
     * Телефон
     */
    private String phone;

    /**
     * Электронная почта
     */
    private String email;

    /**
     * Адрес
     */
    private String address;

    /**
     * Долгота
     */
    private Double longitude;

    /**
     * Широта
     */
    private Double latitude;
}
