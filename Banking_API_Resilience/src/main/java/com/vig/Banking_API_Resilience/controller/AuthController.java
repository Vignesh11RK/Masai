package com.vig.Banking_API_Resilience.controller;

import com.vig.Banking_API_Resilience.config.JwtUtil;
import com.vig.Banking_API_Resilience.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        userService.registerUser(request.getUsername(), request.getPassword());
        logger.info("User registered: {}", request.getUsername());
        return "Registration successful!";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(request.getUsername());
            logger.info("User logged in: {}", request.getUsername());
            return token;
        } else {
            logger.warn("Login failed for: {}", request.getUsername());
            throw new RuntimeException("Invalid login");
        }
    }

    @Data
    static class AuthRequest {
        private String username;
        private String password;
    }
}
