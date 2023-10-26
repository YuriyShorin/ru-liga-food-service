package ru.liga.kitchenservice.mapping;

import org.apache.ibatis.annotations.*;
import ru.liga.model.Restaurant;

import java.util.List;

/**
 * Mapper для ресторанов
 */
@Mapper
public interface RestaurantMapper {

    /**
     * Создать ресторан
     */
    @Insert("INSERT INTO Restaurants (name, status, address, longitude, latitude) " +
            "VALUES (#{name}, #{status}, #{address}, #{longitude}, #{latitude});")
    @SelectKey(statement = "SELECT nextval('restaurants_sequence');", before = true, resultType = Long.class, keyProperty = "id", keyColumn = "id")
    void insertRestaurant(Restaurant restaurant);

    /**
     * Получить рестораны
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
            "LIMIT #{page};")
    List<Restaurant> selectRestaurants(Integer page);

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
     * Изменить ресторан
     */
    @Update("UPDATE Restaurants " +
            "SET name = #{name}, address = #{address}, status = #{status},  longitude = #{longitude}, latitude = #{latitude} " +
            "WHERE id = #{id};")
    void updateRestaurantById(Restaurant restaurant);
}
