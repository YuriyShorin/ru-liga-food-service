package ru.liga.deliveryservice.mapping;

import org.apache.ibatis.annotations.*;
import ru.liga.model.Courier;

import java.util.List;

/**
 * Mapper курьеров
 */
@Mapper
public interface CourierMapper {


    /**
     * Получить курьеров по статусу
     */
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "status", column = "status"),
            @Result(property = "longitude", column = "longitude"),
            @Result(property = "latitude", column = "latitude")
    })
    @Select("SELECT * FROM Couriers " +
            "WHERE status = #{status}")
    List<Courier> selectCourierByStatus(String status);

    /**
     * Изменить курьера
     */
    @Update("UPDATE Couriers " +
            "SET phone = #{phone}, status = #{status},  longitude = #{longitude}, latitude = #{latitude} " +
            "WHERE id = #{id};")
    void updateCourier(Courier courier);
}

