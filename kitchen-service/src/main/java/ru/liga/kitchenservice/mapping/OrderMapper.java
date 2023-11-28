package ru.liga.kitchenservice.mapping;

import org.apache.ibatis.annotations.*;
import ru.liga.model.Order;

import java.util.Optional;
import java.util.UUID;

/**
 * Mapper для заказов
 */
@Mapper
public interface OrderMapper {

    /**
     * Получить заказ по id
     */
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "restaurantId", column = "restaurant_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "courierId", column = "courier_id"),
            @Result(property = "timestamp", column = "timestamp"),
    })
    @Select("SELECT * FROM Orders " +
            "WHERE id = '${id}';")
    Optional<Order> selectOrderById(UUID id);

    /**
     * Изменить заказ
     */
    @Update("UPDATE Orders " +
            "SET customer_id = '${customerId}', restaurant_id = '${restaurantId}', status = #{status}, timestamp = #{timestamp} " +
            "WHERE id = '${id}';")
    void updateOrder(Order order);
}
