package ru.liga.orderservice.mapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.ibatis.annotations.*;

import ru.liga.orderservice.model.Item;
import ru.liga.orderservice.model.Order;
import ru.liga.orderservice.model.Restaurant;
import ru.liga.orderservice.model.RestaurantMenuItem;

import java.util.List;

@Schema(description = "CRUD для заказов")
@Mapper
public interface OrderMapper {

    @Operation(summary = "Получить все заказы")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "restaurantId", column = "restaurant_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "courierId", column = "courier_id"),
            @Result(property = "timestamp", column = "timestamp"),
            @Result(property = "restaurant", column = "restaurant_id", javaType = Restaurant.class, one = @One(select = "selectRestaurantById")),
            @Result(property = "items", column = "id", javaType = List.class, many = @Many(select = "selectItemsByOrderId"))
    })
    @Select("SELECT * FROM Orders")
    List<Order> selectOrders();

    @Operation(summary = "Получить заказ по id")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "restaurantId", column = "restaurant_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "courierId", column = "courier_id"),
            @Result(property = "timestamp", column = "timestamp"),
            @Result(property = "restaurant", column = "restaurant_id", javaType = Restaurant.class, one = @One(select = "selectRestaurantById")),
            @Result(property = "items", column = "id", javaType = List.class, many = @Many(select = "selectItemsByOrderId"))
    })
    @Select("SELECT * FROM Orders " +
            "WHERE id = #{id}")
    Order selectOrderById(Long id);

    @Operation(summary = "Получить ресторан по id")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "status", column = "status"),
            @Result(property = "address", column = "address"),
    })
    @Select("SELECT * FROM Restaurants " +
            "WHERE id = #{id}")
    Restaurant selectRestaurantById(Long id);

    @Operation(summary = "Получить товар по id заказа")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "restaurantMenuItemId", column = "restaurant_menu_item_id"),
            @Result(property = "price", column = "price"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "restaurantMenuItem", column = "restaurant_menu_item_id", javaType = RestaurantMenuItem.class, one = @One(select = "selectRestaurantMenuItemById"))
    })
    @Select("SELECT * FROM Order_items " +
            "WHERE order_id = #{orderId}")
    List<Item> selectItemsByOrderId(Long orderId);

    @Operation(summary = "Получить товар в меню по id")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "restaurantId", column = "restaurant_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "image", column = "image"),
            @Result(property = "description", column = "description"),
    })
    @Select("SELECT * FROM Restaurant_menu_items " +
            "WHERE id = #{id}")
    RestaurantMenuItem selectRestaurantMenuItemById(Long id);

    @Operation(summary = "Создать заказ")
    @Insert("INSERT INTO Orders (id, customer_id, restaurant_id, status, courier_id, timestamp) " +
            "VALUES (#{id}, #{customerId}, #{restaurantId}, #{status}, #{courier_id}, #{timestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertOrder(Order order);
}
