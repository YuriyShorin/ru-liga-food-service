package ru.liga.kitchenservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.liga.dto.ActionDTO;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

/**
 * Feign client для переадресации запросов на порт 8080
 */
@FeignClient(name = "kitchen-client", url = "http://localhost:8080")
public interface KitchenClient {

    /**
     * Обновить статус заказа
     */
    @PostMapping(value = "/delivery/{id}")
    ResponseEntity<?> updateOrderStatus(@PathVariable @Positive Long id, @RequestBody @Valid ActionDTO actionDTO);
}
