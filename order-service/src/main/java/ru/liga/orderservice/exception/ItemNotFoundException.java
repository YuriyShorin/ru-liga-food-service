package ru.liga.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Товар не найден
 */
@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Item not found")
public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException() {
        super();
    }
}
