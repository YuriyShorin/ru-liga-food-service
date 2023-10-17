package ru.liga.kitchenservice.service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.stereotype.Service;

import ru.liga.kitchenservice.dto.GetOrdersResponseDTO;
import ru.liga.kitchenservice.dto.MenuItemDTO;
import ru.liga.kitchenservice.dto.OrdersDTO;

import java.util.List;

@Schema(description = "Сервис для приема заказов на кухню")
@Service
public class KitchenService {

    @Operation(summary = "Получить все заказы")
    public GetOrdersResponseDTO getOrders(String status) {
        return new GetOrdersResponseDTO(List.of(
                new OrdersDTO(1L, List.of(new MenuItemDTO(2, 5L))),
                new OrdersDTO(2L, List.of(new MenuItemDTO(5, 1L))),
                new OrdersDTO(3L, List.of(new MenuItemDTO(6, 15L))),
                new OrdersDTO(4L, List.of(new MenuItemDTO(10, 56L)))), 1, 10);
    }
}
