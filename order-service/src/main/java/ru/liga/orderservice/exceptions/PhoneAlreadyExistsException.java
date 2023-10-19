package ru.liga.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Телефон уже существует
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Customer with such phone already exist!")
public class PhoneAlreadyExistsException extends RuntimeException {

    public PhoneAlreadyExistsException() {
        super();
    }
}
