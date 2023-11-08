package ru.liga.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказ был отменен
 */
@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Order canceled")
public class OrderAlreadyCanceledException extends RuntimeException {

    public OrderAlreadyCanceledException() {
        super();
    }
}
