package ru.liga.authenticationservice.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.liga.authenticationservice.dto.RoleDTO;
import ru.liga.authenticationservice.dto.RegistrationDTO;
import ru.liga.authenticationservice.service.UserService;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody RegistrationDTO request) {
        return userService.createUser(request);
    }

    @PostMapping("/role")
    public ResponseEntity<String> createRole(@RequestBody RoleDTO request) {
        return userService.createRole(request);
    }
}