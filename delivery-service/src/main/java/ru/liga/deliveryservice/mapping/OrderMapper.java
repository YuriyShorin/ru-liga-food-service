package ru.liga.deliveryservice.mapping;

import org.apache.ibatis.annotations.*;

import ru.liga.model.*;

import java.util.List;

/**
 * Mapper для заказов
 */
@Mapper
public interface OrderMapper {

    /**
     * Получить все заказы
     */
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "restaurantId", column = "restaurant_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "courierId", column = "courier_id"),
            @Result(property = "timestamp", column = "timestamp"),
            @Result(property = "restaurant", column = "restaurant_id", javaType = Restaurant.class, one = @One(select = "selectRestaurantById")),
            @Result(property = "items", column = "id", javaType = List.class, many = @Many(select = "selectItemsByOrderId")),
            @Result(property = "customer", column = "customer_id", javaType = Customer.class, one = @One(select = "selectCustomerById"))
    })
    @Select("SELECT * FROM Orders " +
            "WHERE status = #{status};")
    List<Order> selectOrdersByStatus(String status);

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
            @Result(property = "restaurant", column = "restaurant_id", javaType = Restaurant.class, one = @One(select = "selectRestaurantById")),
            @Result(property = "items", column = "id", javaType = List.class, many = @Many(select = "selectItemsByOrderId"))
    })
    @Select("SELECT * FROM Orders " +
            "WHERE id = #{id};")
    Order selectOrderById(Long id);

    /**
     * Получить ресторан по id
     */
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "status", column = "status"),
            @Result(property = "address", column = "address"),
            @Result(property = "longitude", column = "longitude"),
            @Result(property = "latitude", column = "latitude")
    })
    @Select("SELECT * FROM Restaurants " +
            "WHERE id = #{id};")
    Restaurant selectRestaurantById(Long id);

    /**
     * Получить товар по id заказа
     */
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "restaurantMenuItemId", column = "restaurant_menu_item_id"),
            @Result(property = "price", column = "price"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "restaurantMenuItem", column = "restaurant_menu_item_id", javaType = RestaurantMenuItem.class, one = @One(select = "selectRestaurantMenuItemById"))
    })
    @Select("SELECT * FROM Order_items " +
            "WHERE order_id = #{orderId};")
    List<Item> selectItemsByOrderId(Long orderId);

    /**
     * Получить товар в меню по id
     */
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "restaurantId", column = "restaurant_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "image", column = "image"),
            @Result(property = "description", column = "description"),
    })
    @Select("SELECT * FROM Restaurant_menu_items " +
            "WHERE id = #{id};")
    RestaurantMenuItem selectRestaurantMenuItemById(Long id);

    /**
     * Получить заказчика
     */
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email"),
            @Result(property = "address", column = "address"),
            @Result(property = "longitude", column = "longitude"),
            @Result(property = "latitude", column = "latitude")
    })
    @Select("SELECT * FROM Customers " +
            "WHERE id = #{id};")
    Customer selectCustomerById(Long id);

    /**
     * Изменить заказ
     */
    @Update("UPDATE Orders " +
            "SET customer_id = #{customerId}, restaurant_id = #{restaurantId}, status = #{status}, courier_id = #{courierId}, timestamp = #{timestamp} " +
            "WHERE id = #{id};")
    void updateOrder(Order order);
}
