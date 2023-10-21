package ru.liga.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Заказчик не найден
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Customer not found")
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super();
    }
}
