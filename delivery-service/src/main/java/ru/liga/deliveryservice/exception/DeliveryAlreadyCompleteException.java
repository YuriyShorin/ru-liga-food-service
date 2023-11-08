package ru.liga.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Доставка уже заершена
 */
@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Delivery is already complete")
public class DeliveryAlreadyCompleteException extends RuntimeException {

    public DeliveryAlreadyCompleteException() {
        super();
    }
}
