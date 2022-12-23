package com.example.websecurity.service;

import com.example.websecurity.dao.model.User;
import com.example.websecurity.dto.LoginDto;
import com.example.websecurity.security.JwtUserDetails;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AuthenticationService {

    /**
     * Registers a new user in the database by performing a series of quick checks.
     *
     * @return A user object if successfully created
     */
    User registerUser(User user);

    /**
     * Checks if the given email already exists in the database repository or not
     *
     * @return true if the email exists else false
     */
    Boolean usernameAlreadyExists(String username);

    /**
     * Authenticate user and log them in given a loginRequest
     */
    Optional<Authentication> authenticateUser(LoginDto request);

    /**
     * Updates the password of the current logged in user
     */

    //User updatePassword(JwtUserDetails userDetails, UpdatePasswordDto request);

    /**
     * Generates a JWT token for the validated client
     */
    String generateToken(JwtUserDetails userDetails);
}
