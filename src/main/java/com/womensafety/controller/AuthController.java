package com.womensafety.controller;

import com.womensafety.dto.AuthResponse;
import com.womensafety.dto.LoginRequest;
import com.womensafety.dto.RegisterRequest;
import com.womensafety.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CONTROLLER LAYER
 * -----------------
 * Controllers are the "front desk" of our backend: they receive HTTP
 * requests (like a waiter taking an order), pass the work to a Service,
 * and return the result as JSON. No business logic lives here.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
