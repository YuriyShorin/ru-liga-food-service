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

    public Customer(String phone, String email, String address) {
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
