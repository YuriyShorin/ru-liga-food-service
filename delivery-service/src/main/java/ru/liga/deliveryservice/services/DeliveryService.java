package ru.liga.deliveryservice.services;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.liga.deliveryservice.dto.ActionDTO;
import ru.liga.deliveryservice.dto.DeliveryDTO;
import ru.liga.deliveryservice.model.Customer;
import ru.liga.deliveryservice.model.Restaurant;

import java.util.List;

@Schema(description = "Сервис для отправки заказов курьерам")
@Service
public class DeliveryService {

    @Operation(summary = "Получить все доставки")
    public List<DeliveryDTO> getDeliveries(String status) {
        return List.of(
                new DeliveryDTO(1L,
                        new Restaurant("Minina 15", "700 meters"),
                        new Customer("Vaneeva", "3.5 kilometers"), "card"),
                new DeliveryDTO(2L,
                        new Restaurant("Gor'kova 6", "400 meters"),
                        new Customer("Ul'yanova 3", "2.7 kilometers"),
                        "cash"));
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
