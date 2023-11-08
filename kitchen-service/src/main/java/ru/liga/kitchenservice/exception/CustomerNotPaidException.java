package ru.liga.kitchenservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказчик еще не полатил заказ
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Customer did not pay yet")
public class CustomerNotPaidException extends RuntimeException {

    public CustomerNotPaidException() {
        super();
    }
}
