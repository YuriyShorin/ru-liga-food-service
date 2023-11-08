package ru.liga.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказ был оплачен
 */
@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Order canceled")
public class OrderAlreadyPaidException extends RuntimeException {

    public OrderAlreadyPaidException() {
        super();
    }
}
