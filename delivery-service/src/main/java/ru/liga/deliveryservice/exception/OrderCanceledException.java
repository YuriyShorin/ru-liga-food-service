package ru.liga.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказ отменен
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Order canceled")
public class OrderCanceledException extends RuntimeException {

    public OrderCanceledException() {
        super();
    }
}
