package ru.liga.orderservice.mapping;

import org.apache.ibatis.annotations.*;
import ru.liga.model.*;
import java.util.List;
import java.util.UUID;

/**
 * CRUD для заказов
 */
@Mapper
public interface OrderMapper {

    /**
     * Создать заказ
     */
    @Results(value = {
            @Result(property = "id", column = "id")
    })
    @Select("INSERT INTO Orders (customer_id, restaurant_id, status, timestamp) " +
            "VALUES ('${customerId}', '${restaurantId}', #{status}, #{timestamp}) RETURNING id;")
    Id insertOrder(Order order);

    /**
     * Создать товар
     */
    @Insert({"<script>",
            "INSERT INTO Order_items (order_id, restaurant_menu_item_id, price, quantity)",
            "VALUES",
            "<foreach item='item' collection='items'  open='' separator=',' close=''> " +
                    "(" +
                    "'${item.orderId}',",
            "'${item.restaurantMenuItemId}',",
            "#{item.price},",
            "#{item.quantity}" +
                    ")" +
                    "</foreach>",
            "</script>"
    })
    void insertItems(List<Item> items);

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
            @Result(property = "items", column = "id", javaType = List.class, many = @Many(select = "selectItemsByOrderId"))
    })
    @Select("SELECT * FROM Orders " +
            "LIMIT #{pageSize}")
    List<Order> selectOrders(Integer pageSize);

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
            "WHERE id = '${id}';")
    Order selectOrderById(UUID id);

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
            "WHERE id = '${id}';")
    Restaurant selectRestaurantById(UUID id);

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
            "WHERE order_id = '${id}';")
    List<Item> selectItemsByOrderId(UUID id);

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
            "WHERE id = '${id}';")
    RestaurantMenuItem selectRestaurantMenuItemById(UUID id);

    /**
     * Изменить заказ
     */
    @Update("UPDATE Orders " +
            "SET customer_id = '${customerId}', restaurant_id = '${restaurantId}', status = #{status}, timestamp = #{timestamp} " +
            "WHERE id = '${id}';")
    void updateOrder(Order order);
}
