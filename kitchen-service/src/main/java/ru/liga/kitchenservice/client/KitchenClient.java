package ru.liga.kitchenservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.liga.dto.OrderActionDTO;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

/**
 * Feign client для переадресации запросов с порта 8080
 */
@FeignClient(name = "kitchen-client", url = "http://localhost:8080")
public interface KitchenClient {

    @PostMapping(value = "/delivery/{id}")
    ResponseEntity<?> createDelivery(@RequestBody @Valid OrderActionDTO orderActionDTO, @PathVariable @Positive Long id);
}
