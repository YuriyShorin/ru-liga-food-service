package ru.liga.orderservice.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import ru.liga.dto.CoordinatesDTO;
import ru.liga.dto.ItemDTO;
import ru.liga.dto.OrderDTO;
import ru.liga.dto.RestaurantDTO;
import ru.liga.model.Item;
import ru.liga.model.Order;
import ru.liga.orderservice.mapping.OrderMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testGetOrderById_ExistingOrder() {
        ResponseEntity<?> responseEntity = orderService.getOrderById(UUID.randomUUID());

        Order order = orderMapper.selectOrderById(UUID.randomUUID());

        CoordinatesDTO restaurantCoordinates = new CoordinatesDTO(order.getRestaurant().getLongitude(), order.getRestaurant().getLatitude());
        RestaurantDTO restaurantDTO = new RestaurantDTO(order.getRestaurant().getName(), order.getRestaurant().getAddress(), order.getRestaurant().getStatus(), restaurantCoordinates);

        List<ItemDTO> itemsDTOs = new ArrayList<>();
        for (Item item : order.getItems()) {
            itemsDTOs.add(new ItemDTO(item.getRestaurantMenuItem().getPrice() * item.getQuantity(), item.getQuantity(), item.getRestaurantMenuItem().getName(), item.getRestaurantMenuItem().getImage()));
        }

        OrderDTO orderDTO = new OrderDTO(order.getId(), restaurantDTO, order.getTimestamp(), itemsDTOs);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertTrue(responseEntity.getBody() instanceof OrderDTO);
        assertEquals(orderDTO, responseEntity.getBody());
    }

    @Test
    public void testGetOrderById_NonExistingOrder() {
        ResponseEntity<?> responseEntity = orderService.getOrderById(UUID.randomUUID());

        assertEquals(204, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());
    }
}
