package ru.liga.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Ресторан не найден
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Restaurant not found")
public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException() {
        super();
    }
}
