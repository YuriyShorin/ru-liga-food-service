package ru.liga.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Курьер уже в работе
 */
@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Courier is already active")
public class CourierAlreadyActiveException extends RuntimeException {

    public CourierAlreadyActiveException() {
        super();
    }
}
