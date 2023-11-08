package ru.liga.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Курьер не найден
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Courier not found")
public class CourierNotFoundException extends RuntimeException {

    public CourierNotFoundException() {
        super();
    }
}
