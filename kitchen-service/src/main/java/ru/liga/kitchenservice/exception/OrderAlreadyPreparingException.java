package ru.liga.kitchenservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказ уже готовится
 */
@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Order is already preparing")
public class OrderAlreadyPreparingException extends RuntimeException {

    public OrderAlreadyPreparingException() {
        super();
    }
}
