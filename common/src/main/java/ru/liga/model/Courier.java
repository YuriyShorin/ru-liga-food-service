package ru.liga.model;

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

    public Courier(String phone, String status, Double longitude, Double latitude) {
        this.phone = phone;
        this.status = status;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Courier(Long id, String phone, String status, Double longitude, Double latitude) {
        this.id = id;
        this.phone = phone;
        this.status = status;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
