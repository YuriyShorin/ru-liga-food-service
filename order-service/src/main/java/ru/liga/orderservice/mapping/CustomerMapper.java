package ru.liga.orderservice.mapping;

import org.apache.ibatis.annotations.*;
import ru.liga.model.Customer;

import java.util.List;

/**
 * Mapper для заказчиков
 */
@Mapper
public interface CustomerMapper {

    /**
     * Создать заказчика
     */
    @Insert("INSERT INTO Customers(phone, email, address, longitude, latitude) " +
            "VALUES (#{phone}, #{email}, #{address}, #{longitude}, #{latitude});")
    @SelectKey(statement = "SELECT nextval('customers_sequence');", before = true, resultType = Long.class, keyProperty = "id", keyColumn = "id")
    void insertCustomer(Customer customer);

    /**
     * Получить заказчиков
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
            "LIMIT #{page};")
    List<Customer> selectCustomers(Integer page);

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
    Customer selectCustomer(Long id);

    /**
     * Получить заказчика по телефону
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
            "WHERE phone = #{phone};")
    Customer selectCustomerByPhone(String phone);

    /**
     * Получить заказчика по электронной почте
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
            "WHERE email = #{email};")
    Customer selectCustomerByEmail(String email);

    /**
     * Изменить заказчика
     */
    @Update("UPDATE Customers " +
            "SET phone = #{phone}, email = #{email}, address = #{address}, longitude = #{longitude}, latitude = #{latitude} " +
            "WHERE id = #{id};")
    void updateCustomer(Customer customer);
}
