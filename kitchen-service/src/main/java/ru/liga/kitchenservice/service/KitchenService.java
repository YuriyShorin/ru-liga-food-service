package ru.liga.kitchenservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.liga.enums.OrderStatus;
import ru.liga.exception.OrderNotFoundException;
import ru.liga.kitchenservice.exception.*;
import ru.liga.kitchenservice.mapping.OrderMapper;
import ru.liga.model.Order;

import java.util.UUID;

/**
 * Сервис для приема заказов на кухне
 */
@Service
@RequiredArgsConstructor
public class KitchenService {

    /**
     * Mapper для заказов
     */
    private final OrderMapper orderMapper;

    private final RabbitMQProducerService rabbitMQProducerService;

    /**
     * Принять заказ
     */
    @Transactional
    public ResponseEntity<?> accept(UUID id) {
        Order order = orderMapper.selectOrderById(id);

        if (order == null) {
            throw new OrderNotFoundException();
        }

        if (order.getStatus() == OrderStatus.CUSTOMER_CREATED) {
            throw new CustomerNotPaidException();
        }

        if (order.getStatus() == OrderStatus.CUSTOMER_CANCELLED) {
            throw new CustomerCanceledOrderException();
        }

        if (order.getStatus() == OrderStatus.KITCHEN_DECLINED) {
            throw new OrderDeclinedException();
        }

        if (order.getStatus() == OrderStatus.KITCHEN_PREPARING) {
            throw new OrderAlreadyPreparingException();
        }

        if (order.getStatus() == OrderStatus.KITCHEN_FINISHED) {
            throw new OrderAlreadyPreparedException();
        }

        if (order.getStatus() != OrderStatus.CUSTOMER_PAID) {
            throw new OrderAlreadySentForDeliveryException();
        }

        order.setStatus(OrderStatus.KITCHEN_PREPARING);
        orderMapper.updateOrder(order);

        rabbitMQProducerService.sendMessage("Заказ начал готовиться, id заказчика: " + order.getCustomerId() +
                "id ресторана: " + order.getRestaurantId(), "notification");

        return ResponseEntity.ok().build();
    }

    /**
     * Отклонить заказ
     */
    @Transactional
    public ResponseEntity<?> decline(UUID id) {
        Order order = orderMapper.selectOrderById(id);

        if (order == null) {
            throw new OrderNotFoundException();
        }

        if (order.getStatus() == OrderStatus.CUSTOMER_CREATED) {
            throw new CustomerNotPaidException();
        }

        if (order.getStatus() == OrderStatus.CUSTOMER_CANCELLED) {
            throw new CustomerCanceledOrderException();
        }

        if (order.getStatus() == OrderStatus.KITCHEN_DECLINED) {
            throw new OrderDeclinedException();
        }

        if (order.getStatus() == OrderStatus.KITCHEN_PREPARING) {
            throw new OrderAlreadyPreparingException();
        }

        if (order.getStatus() == OrderStatus.KITCHEN_FINISHED) {
            throw new OrderAlreadyPreparedException();
        }

        if (order.getStatus() != OrderStatus.CUSTOMER_PAID) {
            throw new OrderAlreadySentForDeliveryException();
        }

        order.setStatus(OrderStatus.KITCHEN_DECLINED);
        orderMapper.updateOrder(order);

        rabbitMQProducerService.sendMessage("Заказ отменен кухней, id заказчика: " + order.getCustomerId() +
                "id ресторана: " + order.getRestaurantId(), "notification");

        return ResponseEntity.ok().build();
    }

    /**
     * Завершить заказ
     */
    @Transactional
    public ResponseEntity<?> ready(UUID id) {
        Order order = orderMapper.selectOrderById(id);

        if (order == null) {
            throw new OrderNotFoundException();
        }

        if (order.getStatus() == OrderStatus.CUSTOMER_CREATED) {
            throw new CustomerNotPaidException();
        }

        if (order.getStatus() == OrderStatus.CUSTOMER_CANCELLED) {
            throw new CustomerCanceledOrderException();
        }

        if (order.getStatus() == OrderStatus.CUSTOMER_PAID) {
            throw new KitchenNotAcceptedOrDeclinedOrderException();
        }

        if (order.getStatus() == OrderStatus.KITCHEN_DECLINED) {
            throw new OrderDeclinedException();
        }

        if (order.getStatus() == OrderStatus.KITCHEN_FINISHED) {
            throw new OrderAlreadyPreparedException();
        }

        if (order.getStatus() != OrderStatus.KITCHEN_PREPARING) {
            throw new OrderAlreadySentForDeliveryException();
        }

        order.setStatus(OrderStatus.KITCHEN_FINISHED);
        orderMapper.updateOrder(order);

        rabbitMQProducerService.sendMessage("Заказ готов, id заказчика: " + order.getCustomerId() +
                "id ресторана: " + order.getRestaurantId(), "notification");

        return ResponseEntity.ok().build();
    }
}
