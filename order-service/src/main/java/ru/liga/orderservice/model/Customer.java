package ru.liga.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;

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

    public Customer(String phone, String email, String address, Double longitude, Double latitude) {
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
