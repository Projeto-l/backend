package com.medcom.auth.service;


import com.medcom.auth.dto.AuthRequestDTO;
import com.medcom.auth.dto.TokenResponseDTO;
import com.medcom.entity.User;
import com.medcom.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private UUID getIdUser(String email) {
        Optional<User> user = userService.findbyEmail(email);
        return user.map(User::getUserId).orElse(null);
    }

    public TokenResponseDTO authenticate(Authentication authentication) {

        return new TokenResponseDTO(jwtService.generateToken(authentication, getIdUser(authentication.getName())));
    }

    public TokenResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.username(), authRequestDTO.password()));
        return new TokenResponseDTO(jwtService.generateToken(authentication, getIdUser(authentication.getName())));
    }

}
