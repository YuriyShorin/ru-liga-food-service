package ru.liga.orderservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "Model ресторана")
@Data
@AllArgsConstructor
public class Restaurant {

    @Schema(description = "Имя")
    private String name;
}
