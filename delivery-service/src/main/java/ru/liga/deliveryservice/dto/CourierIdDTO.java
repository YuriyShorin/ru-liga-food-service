package ru.liga.deliveryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Schema(description = "DTO доставок")
@Data
public class CourierIdDTO {

    @Schema(description = "Id курьера")
    @JsonProperty("courier_id")
    private UUID courierId;
}
