package ru.liga.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Schema(description = "DTO для получения заказчиков")
@Data
@AllArgsConstructor
public class GetCustomersResponseDTO {

    @Schema(description = "Заказчики")
    private List<CustomerDTO> customerDTOS;

    @Schema(description = "Индекс страницы")
    @JsonProperty("page_index")
    private Integer pageIndex;

    @Schema(description = "Счетчик страниц")
    @JsonProperty("page_count")
    private Integer pageCount;
}
