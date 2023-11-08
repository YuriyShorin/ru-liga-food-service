package ru.liga.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказ уже доставляется
 */
@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Order is already delivering")
public class OrderAlreadyDeliveringException extends RuntimeException {

    public OrderAlreadyDeliveringException() {
        super();
    }
}
