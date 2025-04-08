package com.medcom.auth.controller;

import com.medcom.auth.dto.TokenResponseDTO;
import com.medcom.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping
    public ResponseEntity<TokenResponseDTO> login(Authentication authentication) {
        return ResponseEntity.ok(authService.authenticate(authentication));
    }
}
