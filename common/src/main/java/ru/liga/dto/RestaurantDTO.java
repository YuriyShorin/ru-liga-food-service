package ru.liga.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.liga.enums.RestaurantStatus;

@Schema(description = "DTO ресторана")
@Data
@AllArgsConstructor
public class RestaurantDTO {

    @Schema(description = "Название")
    private String name;

    @Schema(description = "Адрес")
    private String address;

    @Schema(description = "Стасус")
    private RestaurantStatus status;

    @Schema(description = "Coordinates")
    private CoordinatesDTO coordinates;
}
