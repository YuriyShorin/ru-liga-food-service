package ru.liga.orderservice.mapping;

import org.apache.ibatis.annotations.*;
import ru.liga.orderservice.model.*;
import java.util.List;

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
            "VALUES (#{customerId}, #{restaurantId}, #{status}, #{timestamp}) RETURNING id;")
    @SelectKey(statement = "SELECT nextval('orders_sequence');", before = true, resultType = Long.class, keyProperty = "id", keyColumn = "id")
    Id insertOrder(Order order);

    /**
     * Создать товар
     */
    @Insert({"<script>",
            "INSERT INTO Order_items (order_id, restaurant_menu_item_id, price, quantity)",
            "VALUES",
            "<foreach item='item' collection='items'  open='' separator=',' close=''> " +
                    "(" +
                    "#{item.orderId},",
            "#{item.restaurantMenuItemId},",
            "#{item.price},",
            "#{item.quantity}" +
                    ")" +
                    "</foreach>",
            "</script>"
    })
    @SelectKey(statement = "SELECT nextval('order_items_sequence');", before = true, resultType = Long.class, keyProperty = "id", keyColumn = "id")
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
    @Select("SELECT * FROM Orders;")
    List<Order> selectOrders();

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
}
