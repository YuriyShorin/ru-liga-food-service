package ru.liga.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Электронная почта уже существует
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Customer with such email already exist!")
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super();
    }
}
