package ru.liga.orderservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Модель ресторана")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Schema(description = "Id")
    private Long id;

    @Schema(description = "Статус")
    private String status;

    @Schema(description = "Адрес")
    private String address;

    @Schema(description = "Заказы")
    private List<Order> orders;

    public Restaurant(Long id, String status, String address) {
        this.id = id;
        this.status = status;
        this.address = address;
    }
}
