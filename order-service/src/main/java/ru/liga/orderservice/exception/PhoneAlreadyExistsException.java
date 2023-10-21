package ru.liga.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Телефон уже существует
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Customer with such phone already exist!")
public class PhoneAlreadyExistsException extends RuntimeException {

    public PhoneAlreadyExistsException() {
        super();
    }
}
