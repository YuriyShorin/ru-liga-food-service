package ru.liga.kitchenservice.services;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.stereotype.Service;

import ru.liga.kitchenservice.dto.OrdersDTO;
import ru.liga.kitchenservice.model.MenuItems;

import java.util.List;

@Schema(description = "Сервис для приема заказов на кухню")
@Service
public class KitchenService {

    @Operation(summary = "Получить все заказы")
    public List<OrdersDTO> getOrders(String status) {
        return List.of(
                new OrdersDTO(1L, List.of(new MenuItems(2, 5L))),
                new OrdersDTO(2L, List.of(new MenuItems(5, 1L))),
                new OrdersDTO(3L, List.of(new MenuItems(6, 15L))),
                new OrdersDTO(4L, List.of(new MenuItems(10, 56L))));
    }
}
