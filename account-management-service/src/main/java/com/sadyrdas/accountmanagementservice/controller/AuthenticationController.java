package com.sadyrdas.accountmanagementservice.controller;

import com.sadyrdas.accountmanagementservice.dto.LoginResponse;
import com.sadyrdas.accountmanagementservice.dto.LoginUserDTO;
import com.sadyrdas.accountmanagementservice.dto.UserRequest;
import com.sadyrdas.accountmanagementservice.model.Client;
import com.sadyrdas.accountmanagementservice.model.User;
import com.sadyrdas.accountmanagementservice.service.ClientService;
import com.sadyrdas.accountmanagementservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final ClientService clientService;

    @PostMapping("/signUp")
    public ResponseEntity<Client> signUp(@RequestBody UserRequest userRequest) {
        Client registeredClient = clientService.registerClient(userRequest);
        return ResponseEntity.ok(registeredClient);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        Client authenticatedUser = clientService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();

        return ResponseEntity.ok(loginResponse);
    }
}
