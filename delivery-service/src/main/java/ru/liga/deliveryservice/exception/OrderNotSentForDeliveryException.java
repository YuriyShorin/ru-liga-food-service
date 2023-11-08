package ru.liga.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Заказ еще не отправлен в доставку
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Order did not send to delivery yet")
public class OrderNotSentForDeliveryException extends RuntimeException {

    public OrderNotSentForDeliveryException() {
        super();
    }
}
