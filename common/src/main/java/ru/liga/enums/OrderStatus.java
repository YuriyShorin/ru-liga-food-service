package ru.liga.enums;

/**
 * Статусы заказа
 */
public enum OrderStatus {

    CUSTOMER_CREATED,
    CUSTOMER_PAID,
    CUSTOMER_CANCELLED,
    KITCHEN_PREPARING,
    KITCHEN_DECLINED,
    KITCHEN_FINISHED,
    DELIVERY_DELIVERING,
    DELIVERY_COMPLETE
}
