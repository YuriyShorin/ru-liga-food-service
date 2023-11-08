package ru.liga.kitchenservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказ уже отправлен в доставку
 */
@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = "Order already sent for delivery")
public class OrderAlreadySentForDeliveryException extends RuntimeException {

    public OrderAlreadySentForDeliveryException() {
        super();
    }
}
