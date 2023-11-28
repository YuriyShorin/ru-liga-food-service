package ru.liga.orderservice.mapping;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.liga.model.Customer;

import java.util.Optional;
import java.util.UUID;

@Mapper
public interface CustomerMapper {

    /**
     * Получить заказчика по id
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
            "WHERE id = '${id}';")
    Optional<Customer> selectCustomerById(UUID id);
}
