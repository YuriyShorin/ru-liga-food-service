package ru.liga.orderservice.services;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import ru.liga.orderservice.dto.CreateOrderRequestDTO;
import ru.liga.orderservice.dto.CreateOrderResponseDTO;
import ru.liga.orderservice.dto.GetOrderResponseDTO;
import ru.liga.orderservice.model.Item;
import ru.liga.orderservice.model.Restaurant;

import java.util.List;

@Schema(description = "Сервис для оформления заказов")
@Service
public class OrderService {

    @Operation(summary = "Получить все заказы")
    public List<GetOrderResponseDTO> getOrders() {
        return List.of(
                new GetOrderResponseDTO(1L, new Restaurant("Вкусно и точка"), List.of(new Item(315.0, 1, "Биг спешиал комбо", "Тут будет картинка"))),
                new GetOrderResponseDTO(2L, new Restaurant("Burger King"), List.of(new Item(90.0, 1, "Наггетсы", "Тут будет картинка"))));
    }

    @Operation(summary = "Получить заказ по id")
    public GetOrderResponseDTO getOrderById(@PathVariable Long id) {
        return new GetOrderResponseDTO(id, new Restaurant("Вкусно и точка"), List.of(new Item(315.0, 1, "Биг спешиал комбо", "Тут будет картинка")));
    }

    @Operation(summary = "Создать новый заказ")
    public CreateOrderResponseDTO createOrder(@RequestBody CreateOrderRequestDTO createOrderResponseDTO) {
        return new CreateOrderResponseDTO(1L, "https://someurl.com", "18:15");
    }
}
