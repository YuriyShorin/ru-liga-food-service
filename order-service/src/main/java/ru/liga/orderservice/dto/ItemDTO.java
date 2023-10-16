package ru.liga.orderservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO товара")
@Data
@AllArgsConstructor
public class ItemDTO {

    @Schema(description = "Цена")
    private Double price;

    @Schema(description = "Количество")
    private Integer quantity;

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Картика")
    private String image;
}