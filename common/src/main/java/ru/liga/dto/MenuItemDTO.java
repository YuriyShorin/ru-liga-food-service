package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Schema(description = "DTO меню")
@Data
@AllArgsConstructor
public class MenuItemDTO {

    @Schema(description = "Количество")
    @Min(1)
    private Integer quantity;

    @Schema(description = "Id товара в меню")
    @JsonProperty("menu_item_id")
    @NotNull(message = "Id товара в меню не может быть пустым")
    private UUID menuItemId;
}
