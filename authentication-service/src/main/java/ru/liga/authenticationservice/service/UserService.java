package ru.liga.authenticationservice.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import ru.liga.authenticationservice.dto.RoleDTO;
import ru.liga.authenticationservice.dto.RegistrationDTO;
import ru.liga.authenticationservice.model.CustomUserDetails;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDetailsService userDetailsService;

    @Qualifier("defaultPasswordEncoder")
    private final PasswordEncoder defaultPasswordEncoder;

    public UserService(UserDetailsService userDetailsService, PasswordEncoder defaultPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
    }

    public ResponseEntity<String> createUser(RegistrationDTO request) {

        CustomUserDetails customUserDetails = new CustomUserDetails(
                request.getUsername(), defaultPasswordEncoder.encode(request.getPassword()),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );

        try {
            if (userDetailsService.loadUserByUsername(request.getUsername()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь с таким именем уже существует");
            }

        } catch (UsernameNotFoundException e) {
            ((JdbcUserDetailsManager) userDetailsService).createUser(customUserDetails);
            return ResponseEntity.ok("Пользователь успешно создан");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании пользователя");
    }

    public ResponseEntity<String> createRole(RoleDTO request) {
        UserDetails details = userDetailsService.loadUserByUsername(request.getUsername());

        if (details == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь не найден!");
        }

        List<GrantedAuthority> grantedAuthorities = details.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
        grantedAuthorities.add(new SimpleGrantedAuthority(request.getRole()));

        UserDetails newDetails = new CustomUserDetails(details.getUsername(), details.getPassword(), grantedAuthorities);
        ((JdbcUserDetailsManager) userDetailsService).updateUser(newDetails);

        return ResponseEntity.ok("Роль успешно добавлена!");
    }
}
