package ru.liga.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Товар из другого ресторана
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Item is from other restaurant")
public class ItemFromOtherRestaurantException extends RuntimeException {

    public ItemFromOtherRestaurantException() {
        super();
    }
}
