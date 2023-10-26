package ru.liga.kitchenservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.liga.dto.RestaurantDTO;
import ru.liga.kitchenservice.exception.RestaurantNotFoundException;
import ru.liga.kitchenservice.mapping.RestaurantMapper;
import ru.liga.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с ресторанами
 */
@Service
@RequiredArgsConstructor
public class RestaurantService {

    /**
     * Mapper для ресторанов
     */
    private final RestaurantMapper restaurantMapper;

    /**
     * Создать ресторан
     */
    public ResponseEntity<?> createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant(restaurantDTO.getName(), restaurantDTO.getAddress(), restaurantDTO.getStatus(), restaurantDTO.getLongitude(), restaurantDTO.getLatitude());
        restaurantMapper.insertRestaurant(restaurant);

        return ResponseEntity.ok().build();
    }

    /**
     * Получить все рестораны
     */
    public List<RestaurantDTO> getRestaurants() {
        List<Restaurant> restaurants = restaurantMapper.selectRestaurants();
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            restaurantDTOS.add(new RestaurantDTO(restaurant.getName(), restaurant.getAddress(), restaurant.getStatus(), restaurant.getLongitude(), restaurant.getLatitude()));
        }

        return restaurantDTOS;
    }

    /**
     * Получить рестораны по id
     */
    public RestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantMapper.selectRestaurantById(id);

        if (restaurant == null) {
            throw new RestaurantNotFoundException();
        }

        return new RestaurantDTO(restaurant.getName(), restaurant.getAddress(), restaurant.getStatus(), restaurant.getLongitude(), restaurant.getLatitude());
    }

    /**
     * Изменить ресторан
     */
    public ResponseEntity<?> updateRestaurant(Long id, RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant(id, restaurantDTO.getName(), restaurantDTO.getAddress(), restaurantDTO.getStatus(), restaurantDTO.getLongitude(), restaurantDTO.getLatitude());
        restaurantMapper.updateRestaurantById(restaurant);

        return ResponseEntity.ok().build();
    }
}
