package ru.liga.orderservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.liga.orderservice.dto.CustomerDTO;
import ru.liga.orderservice.service.CustomerService;

import java.util.List;

@Tag(name = "API для работы с заказчиками")
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Schema(description = "Сервис для CustomerController")
    private final CustomerService customerService;

    @Operation(summary = "Создать заказчика")
    @PostMapping("/")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @Operation(summary = "Получить заказчиков")
    @GetMapping("/")
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomers();
    }

    @Operation(summary = "Получить заказчиков по Id")
    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @Operation(summary = "Изменить заказчика по id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        return customerService.updateCustomer(id, customerDTO);
    }
}
