package ru.liga.authenticationservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import ru.liga.authenticationservice.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserAuthenticationConverter implements AuthenticationConverter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Authentication convert(HttpServletRequest request) {
        UserDTO userDTO;
        try {
            userDTO = MAPPER.readValue(request.getInputStream(), UserDTO.class);
        } catch (IOException e) {
            return null;
        }
        return UsernamePasswordAuthenticationToken.unauthenticated(userDTO.getLogin(), userDTO.getPassword());
    }
}
