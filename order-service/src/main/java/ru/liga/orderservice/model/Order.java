package ru.liga.orderservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Schema(description = "Модель заказа")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Schema(description = "Id")
    private Long id;

    @Schema(description = "Id заказчика")
    private Long customerId;

    @Schema(description = "Id ресторана")
    private Long restaurantId;

    @Schema(description = "Статус")
    private String status;

    @Schema(description = "Id курьера")
    private Long courierId;

    @Schema(description = "Время заказа")
    private Timestamp timestamp;

    @Schema(description = "Ресторан")
    private Restaurant restaurant;

    @Schema(description = "Товары")
    private List<Item> items;

    public Order(Long id, Long customerId, Long restaurantId, String status, Long courierId, Timestamp timestamp) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.courierId = courierId;
        this.timestamp = timestamp;
    }

}
