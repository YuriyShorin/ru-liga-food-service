package ru.liga.model;

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
     * Название
     */
    private String name;

    /**
     * Адрес
     */
    private String address;

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
     * Заказы
     */
    private List<Order> orders;

    public Restaurant(Long id, String name, String address, String status, Double longitude, Double latitude) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Restaurant(String name, String address, String status, Double longitude, Double latitude) {
        this.name = name;
        this.status = status;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
