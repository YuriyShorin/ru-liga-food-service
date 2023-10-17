package ru.liga.deliveryservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.liga.deliveryservice.dto.*;

import java.util.List;

@Schema(description = "Сервис для отправки заказов курьерам")
@Service
public class DeliveryService {

    @Operation(summary = "Получить все доставки")
    public GetDeliveriesResponseDTO getDeliveries(String status) {
        return new GetDeliveriesResponseDTO(List.of(
                new DeliveryDTO(1L,
                        new RestaurantDTO("Minina 15", 0.7),
                        new CustomerDTO("Vaneeva", 3.5), "card"),
                new DeliveryDTO(2L,
                        new RestaurantDTO("Gor'kova 6", 0.4),
                        new CustomerDTO("Ul'yanova 3", 2.7),
                        "cash")), 1, 10);
    }

    @Operation(summary = "Создать доставку")
    public ResponseEntity<?> createDelivery(Long id, ActionDTO actionDTO) {
        System.out.println(actionDTO.getOrderAction());
        switch (actionDTO.getOrderAction()) {
            case "accept":
            case "completed":
                return ResponseEntity.status(HttpStatus.OK).build();
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
