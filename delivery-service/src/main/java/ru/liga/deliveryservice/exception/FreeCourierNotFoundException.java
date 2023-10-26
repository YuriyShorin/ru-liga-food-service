package ru.liga.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Свободный курьер не найден
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Free courier is not found")
public class FreeCourierNotFoundException extends RuntimeException {

    public FreeCourierNotFoundException() {
        super();
    }
}