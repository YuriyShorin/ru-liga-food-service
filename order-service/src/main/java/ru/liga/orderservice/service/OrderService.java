package ru.liga.orderservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.liga.dto.*;
import ru.liga.enums.OrderStatus;
import ru.liga.enums.RestaurantStatus;
import ru.liga.exception.OrderNotFoundException;
import ru.liga.model.*;
import ru.liga.orderservice.dto.CreateOrderRequestDTO;
import ru.liga.orderservice.dto.CreateOrderResponseDTO;
import ru.liga.orderservice.dto.GetOrdersResponseDTO;
import ru.liga.orderservice.exception.*;
import ru.liga.orderservice.mapping.CustomerMapper;
import ru.liga.orderservice.mapping.OrderMapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;


/**
 * Сервис для оформления заказов
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    /**
     * Mapper для заказов
     */
    private final OrderMapper orderMapper;

    /**
     * Mapper для заказчиков
     */
    private final CustomerMapper customerMapper;

    /**
     * Сервис для отправки сообщений RabbitMQ
     */
    private final RabbitMQProducerService rabbitMQProducerService;

    /**
     * Создать новый заказ
     */
    @Transactional
    public ResponseEntity<?> createOrder(CreateOrderRequestDTO createOrderResponseDTO) {
        Restaurant restaurant = orderMapper.selectRestaurantById(createOrderResponseDTO.getRestaurantId()).orElseThrow(RestaurantNotFoundException::new);

        if (restaurant.getStatus() == RestaurantStatus.NOT_ACTIVE) {
            throw new RestaurantNotActiveException();
        }

        Customer customer = customerMapper.selectCustomerById(createOrderResponseDTO.getCustomerId()).orElseThrow(CustomerNotFoundException::new);

        List<MenuItemDTO> menuItemDTOS = createOrderResponseDTO.getMenuItems();
        for (MenuItemDTO menuItemDTO : menuItemDTOS) {
            RestaurantMenuItem item = orderMapper.selectRestaurantMenuItemById(menuItemDTO.getMenuItemId()).orElseThrow(ItemNotFoundException::new);

            if (!item.getRestaurantId().equals(restaurant.getId())) {
                throw new ItemFromOtherRestaurantException();
            }
        }

        Order order = new Order(customer.getId(), restaurant.getId(), OrderStatus.CUSTOMER_CREATED, Timestamp.from(Instant.now()));
        UUID orderId = orderMapper.insertOrder(order).orElseThrow(OrderNotFoundException::new).getId();

        List<Item> items = new ArrayList<>();
        for (MenuItemDTO menuItemDTO : createOrderResponseDTO.getMenuItems()) {
            RestaurantMenuItem restaurantMenuItem = orderMapper.selectRestaurantMenuItemById(menuItemDTO.getMenuItemId()).orElseThrow(ItemNotFoundException::new);

            if (!restaurantMenuItem.getRestaurantId().equals(restaurant.getId())) {
                throw new ItemFromOtherRestaurantException();
            }

            Item item = new Item(orderId, menuItemDTO.getMenuItemId(), restaurantMenuItem.getPrice() * menuItemDTO.getQuantity(), menuItemDTO.getQuantity());
            items.add(item);
        }

        orderMapper.insertItems(items);

        rabbitMQProducerService.sendMessage("Заказ был создан, id заказчика: " + order.getCustomerId(), "notification");

        return ResponseEntity.ok(new CreateOrderResponseDTO(orderId, "Secure url", Date.from(order.getTimestamp().toInstant().plusSeconds(3600))));
    }

    /**
     * Получить все заказы
     */
    @Transactional(readOnly = true)
    public ResponseEntity<?> getOrders(Integer pageIndex, Integer pageCount) {
        Pageable page = PageRequest.of(pageIndex / pageCount, pageCount);

        List<Order> orders = orderMapper.selectOrders(page.getPageSize());
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders) {
            List<ItemDTO> itemDTOS = new ArrayList<>();

            for (Item item : order.getItems()) {
                itemDTOS.add(new ItemDTO(item.getRestaurantMenuItem().getPrice() * item.getQuantity(), item.getQuantity(), item.getRestaurantMenuItem().getName(), item.getRestaurantMenuItem().getImage()));
            }

            CoordinatesDTO restaurantCoordinates = new CoordinatesDTO(order.getRestaurant().getLongitude(), order.getRestaurant().getLatitude());
            RestaurantDTO restaurantDTO = new RestaurantDTO(order.getRestaurant().getName(), order.getRestaurant().getAddress(), order.getRestaurant().getStatus(), restaurantCoordinates);
            orderDTOS.add(new OrderDTO(order.getId(), restaurantDTO, order.getTimestamp(), itemDTOS));
        }

        return ResponseEntity.ok(new GetOrdersResponseDTO(orderDTOS, pageIndex, pageCount));
    }


    /**
     * Получить заказ по id
     */
    @Transactional(readOnly = true)
    public ResponseEntity<?> getOrderById(UUID id) {
        Order order = orderMapper.selectOrderById(id).orElseThrow(OrderNotFoundException::new);

        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : order.getItems()) {
            itemDTOS.add(new ItemDTO(item.getRestaurantMenuItem().getPrice() * item.getQuantity(), item.getQuantity(), item.getRestaurantMenuItem().getName(), item.getRestaurantMenuItem().getImage()));
        }

        CoordinatesDTO restaurantCoordinates = new CoordinatesDTO(order.getRestaurant().getLongitude(), order.getRestaurant().getLatitude());
        RestaurantDTO restaurantDTO = new RestaurantDTO(order.getRestaurant().getName(), order.getRestaurant().getAddress(), order.getRestaurant().getStatus(), restaurantCoordinates);

        return ResponseEntity.ok(new OrderDTO(order.getId(), restaurantDTO, order.getTimestamp(), itemDTOS));
    }

    /**
     * Оплатить заказ
     */
    @Transactional
    public ResponseEntity<?> pay(UUID id) {
        Order order = orderMapper.selectOrderById(id).orElseThrow(OrderNotFoundException::new);

        if (order.getStatus() == OrderStatus.CUSTOMER_CANCELLED) {
            throw new OrderAlreadyCanceledException();
        }

        if (order.getStatus() != OrderStatus.CUSTOMER_CREATED) {
            throw new OrderAlreadyPaidException();
        }

        order.setStatus(OrderStatus.CUSTOMER_PAID);
        orderMapper.updateOrder(order);

        rabbitMQProducerService.sendMessage("Заказ был оплачен, id заказчика: " + order.getCustomerId() + " заказ: " + order, "notification");

        return ResponseEntity.ok().build();
    }

    /**
     * Отменить заказ
     */
    @Transactional
    public ResponseEntity<?> cancel(UUID id) {
        Order order = orderMapper.selectOrderById(id).orElseThrow(OrderNotFoundException::new);

        if (order.getStatus() == OrderStatus.CUSTOMER_CANCELLED) {
            throw new OrderAlreadyCanceledException();
        }

        if (order.getStatus() != OrderStatus.CUSTOMER_CREATED) {
            throw new OrderAlreadyPaidException();
        }

        order.setStatus(OrderStatus.CUSTOMER_CANCELLED);
        orderMapper.updateOrder(order);

        rabbitMQProducerService.sendMessage("Заказ был отменен, id заказчика: " + order.getCustomerId(), "notification");

        return ResponseEntity.ok().build();
    }
}
