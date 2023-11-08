package ru.liga.kitchenservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказчик отменил заказ
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Customer canceled order")
public class CustomerCanceledOrderException extends RuntimeException {

    public CustomerCanceledOrderException() {
        super();
    }
}
