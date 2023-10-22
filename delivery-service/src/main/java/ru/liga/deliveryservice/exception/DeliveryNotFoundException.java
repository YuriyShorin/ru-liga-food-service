package ru.liga.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Доставка не найдена
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Delivery not found")
public class DeliveryNotFoundException extends RuntimeException {

    public DeliveryNotFoundException() {
        super();
    }
}
