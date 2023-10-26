package ru.liga.deliveryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.liga.deliveryservice.mapping.CourierMapper;
import ru.liga.dto.CourierDTO;
import ru.liga.dto.GetCouriersResponseDTO;
import ru.liga.exception.CourierNotFoundException;
import ru.liga.model.Courier;

import java.util.ArrayList;
import java.util.List;


/**
 * Сервис для работы с курьерами
 */
@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierMapper courierMapper;

    /**
     * Создать курьера
     */
    public ResponseEntity<?> createCourier(CourierDTO courierDTO) {
        Courier courier = new Courier(courierDTO.getPhone(), courierDTO.getStatus(), courierDTO.getLongitude(), courierDTO.getLatitude());
        courierMapper.insertCourier(courier);

        return ResponseEntity.ok().build();
    }

    /**
     * Получить всех курьеров
     */
    public GetCouriersResponseDTO getCouriers(Integer pageIndex, Integer pageCount) {
        Pageable page = PageRequest.of(pageIndex / pageCount, pageCount);

        List<Courier> couriers = courierMapper.selectCouriers(page.getPageSize());
        List<CourierDTO> courierDTOS = new ArrayList<>();

        for (Courier courier : couriers) {
            courierDTOS.add(new CourierDTO(courier.getPhone(), courier.getStatus(), courier.getLongitude(), courier.getLatitude()));
        }

        return new GetCouriersResponseDTO(courierDTOS, pageIndex, pageCount);
    }

    /**
     * Получить курьера по id
     */
    public CourierDTO getCourier(Long id) {
        Courier courier = courierMapper.selectCourierById(id);

        if (courier == null) {
            throw new CourierNotFoundException();
        }

        return new CourierDTO(courier.getPhone(), courier.getStatus(), courier.getLongitude(), courier.getLatitude());
    }

    /**
     * Изменить курьера
     */
    public ResponseEntity<?> updateCourier(Long id, CourierDTO courierDTO) {
        Courier courier = courierMapper.selectCourierById(id);
        if (courier == null) {
            throw new CourierNotFoundException();
        }

        courier = new Courier(id, courierDTO.getPhone(), courierDTO.getStatus(), courierDTO.getLongitude(), courierDTO.getLatitude());
        courierMapper.updateCourier(courier);

        return ResponseEntity.ok().build();
    }
}
