package ru.liga.deliveryservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import ru.liga.exception.FreeCourierNotFoundException;
import ru.liga.deliveryservice.mapping.CourierMapper;
import ru.liga.deliveryservice.mapping.OrderMapper;
import ru.liga.model.Courier;
import ru.liga.enums.CourierStatus;
import ru.liga.model.Order;

import java.util.List;

/**
 * Listener RabbitMQ
 */
@Service
@RequiredArgsConstructor
public class QueueListener {

    /**
     * Mapper курьеров
     */
    private final CourierMapper courierMapper;

    /**
     * Mapper заказов
     */
    private final OrderMapper orderMapper;

    /**
     * Найти свободного курьера
     */
    @RabbitListener(queues = {"courier1", "courier2"})
    public void findCouriers(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long orderId = objectMapper.readValue(message, Long.class);

        List<Courier> couriers = courierMapper.selectCourierByStatus(CourierStatus.FREE.name());
        Order order = orderMapper.selectOrderById(orderId);

        if (!couriers.isEmpty()) {
            Courier courier = couriers.get(0);
            courier.setStatus(CourierStatus.ACTIVE.name());

            order.setCourierId(courier.getId());
        } else {
            throw new FreeCourierNotFoundException();
        }

        orderMapper.updateOrder(order);
    }
}
