package ru.liga.kitchenservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Кухня еще не взяла заказ или не отказалась от него
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Kitchen did not accept or decline order yet")
public class KitchenNotAcceptedOrDeclinedOrderException extends RuntimeException {

    public KitchenNotAcceptedOrDeclinedOrderException() {
        super();
    }
}
