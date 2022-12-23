package com.example.websecurity.api;

import com.example.websecurity.dto.LoginDto;
import com.example.websecurity.dto.RegistrationDto;
import com.example.websecurity.dto.UserDto;
import com.example.websecurity.mapper.UserMapper;
import com.example.websecurity.security.JwtUserDetails;
import com.example.websecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final AuthenticationService service;

    @PermitAll
    @PostMapping("/register")
    public UserDto register(@RequestBody @Valid @NotNull(message = "user should be present") RegistrationDto registrationDto) {
        var user = UserMapper.INSTANCE.toEntity(registrationDto);
        return UserMapper.INSTANCE.toDto(service.registerUser(user));
    }

    @PermitAll
    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto dto) {
        return service.authenticateUser(dto)
                .map(auth -> service.generateToken((JwtUserDetails) auth.getPrincipal()))
                .orElseThrow(() -> new IllegalStateException("User not authenticated"));
    }
}
