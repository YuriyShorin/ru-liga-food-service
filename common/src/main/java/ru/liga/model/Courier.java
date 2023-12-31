package ru.liga.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.enums.CourierStatus;

import java.util.UUID;

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
    private UUID id;

    /**
     * Телефон
     */
    private String phone;

    /**
     * Статус
     */
    private CourierStatus status;

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
