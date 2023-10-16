package ru.liga.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Schema(description = "DTO для получения всех заказов")
@Data
@AllArgsConstructor
public class GetOrdersResponseDTO {

    @Schema(description = "Заказы")
    private List<OrderDTO> orders;

    @Schema(description = "Индекс страницы")
    @JsonProperty("page_index")
    private Integer pageIndex;

    @Schema(description = "Счетчик страницы")
    @JsonProperty("page_count")
    private Integer pageCount;
}
