package ru.liga.orderservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.liga.dto.CoordinatesDTO;
import ru.liga.dto.CustomerDTO;
import ru.liga.dto.GetCustomersResponseDTO;
import ru.liga.exception.CustomerNotFoundException;
import ru.liga.exception.EmailAlreadyExistsException;
import ru.liga.exception.PhoneAlreadyExistsException;
import ru.liga.orderservice.mapping.CustomerMapper;
import ru.liga.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с заказчиками
 */
@Service
@RequiredArgsConstructor
public class CustomerService {

    /**
     * Mapper для заказчиков
     */
    private final CustomerMapper customerMapper;

    /**
     * Создать заказчика
     */
    public ResponseEntity<?> createCustomer(CustomerDTO customerDTO) {
        if (customerMapper.selectCustomerByPhone(customerDTO.getPhone()) != null) {
            throw new PhoneAlreadyExistsException();
        }

        if (customerMapper.selectCustomerByEmail(customerDTO.getEmail()) != null) {
            throw new EmailAlreadyExistsException();
        }

        Customer customer = new Customer(customerDTO.getPhone(), customerDTO.getEmail(), customerDTO.getAddress(), customerDTO.getCoordinates().getLongitude(), customerDTO.getCoordinates().getLatitude());
        customerMapper.insertCustomer(customer);

        return ResponseEntity.ok().build();
    }

    /**
     * Получить заказчиков
     */
    public GetCustomersResponseDTO getCustomers(Integer pageIndex, Integer pageCount) {
        Pageable page = PageRequest.of(pageIndex / pageCount, pageCount);

        List<Customer> customers = customerMapper.selectCustomers(page.getPageSize());
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getPhone(), customer.getEmail(), customer.getAddress(), new CoordinatesDTO(customer.getLongitude(), customer.getLatitude())));
        }

        return new GetCustomersResponseDTO(customerDTOS, pageIndex, pageCount);
    }

    /**
     * Получить заказчика
     */
    public CustomerDTO getCustomer(Long id) {
        Customer customer = customerMapper.selectCustomer(id);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        return new CustomerDTO(customer.getPhone(), customer.getEmail(), customer.getAddress(), new CoordinatesDTO(customer.getLongitude(), customer.getLatitude()));
    }

    /**
     * Изменить заказчиков
     */
    public ResponseEntity<?> updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.selectCustomer(id);

        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        customer = customerMapper.selectCustomerByPhone(customerDTO.getPhone());
        if (customer != null && !customer.getId().equals(id)) {
            throw new PhoneAlreadyExistsException();
        }

        customer = customerMapper.selectCustomerByEmail(customerDTO.getEmail());
        if (customer != null && !customer.getId().equals(id)) {
            throw new EmailAlreadyExistsException();
        }

        customer = new Customer(id, customerDTO.getPhone(), customerDTO.getEmail(), customerDTO.getAddress(), customerDTO.getCoordinates().getLongitude(), customerDTO.getCoordinates().getLatitude());
        customerMapper.updateCustomer(customer);

        return ResponseEntity.ok().build();
    }
}
