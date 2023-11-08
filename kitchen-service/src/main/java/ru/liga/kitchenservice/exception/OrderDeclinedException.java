package ru.liga.kitchenservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказ отменен
 */
@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Order already declined")
public class OrderDeclinedException extends RuntimeException {

    public OrderDeclinedException() {
        super();
    }
}
