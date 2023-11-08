package ru.liga.deliveryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Кухня еще не приготовила заказ
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Kitchen not prepared order")
public class KitchenNotPreparedException extends RuntimeException {

    public KitchenNotPreparedException() {
        super();
    }
}
