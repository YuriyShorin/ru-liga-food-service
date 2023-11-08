package ru.liga.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Ресторан не активен
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Restaurant is not active")
public class RestaurantNotActiveException extends RuntimeException {

    public RestaurantNotActiveException() {
        super();
    }
}
