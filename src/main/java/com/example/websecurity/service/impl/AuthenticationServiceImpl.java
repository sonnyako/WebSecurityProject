package com.example.websecurity.service.impl;

import com.example.websecurity.dao.model.User;
import com.example.websecurity.dto.LoginDto;
import com.example.websecurity.security.JwtTokenProvider;
import com.example.websecurity.security.JwtUserDetails;
import com.example.websecurity.security.JwtUserDetailsService;
import com.example.websecurity.service.AuthenticationService;
import com.example.websecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    @Override
    public User registerUser(User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            throw new EntityExistsException("User already exists");
        }
        return userDetailsService.save(user);
    }

    @Override
    public Boolean usernameAlreadyExists(String username) {
        return userService.findByUsername(username) != null;
    }

    @Override
    public Optional<Authentication> authenticateUser(LoginDto request) {
        log.info("Executing authenticateUser into AuthenticationService");

        var authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        return Optional.ofNullable(authenticationManager.authenticate(authentication));
    }

    @Override
    public String generateToken(JwtUserDetails userDetails) {
        return tokenProvider.generateToken(userDetails.getUser());
    }
}
