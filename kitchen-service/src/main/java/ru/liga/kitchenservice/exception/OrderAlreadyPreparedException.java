package ru.liga.kitchenservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказ уже приготовлен
 */
@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Order already prepared")
public class OrderAlreadyPreparedException extends RuntimeException {

    public OrderAlreadyPreparedException() {
        super();
    }
}
