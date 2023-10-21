package ru.liga.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказ не найден
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Order not found")
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super();
    }
}
