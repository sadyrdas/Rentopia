package com.sadyrdas.accountmanagementservice.controller;

import com.sadyrdas.accountmanagementservice.dto.UserRequest;
import com.sadyrdas.accountmanagementservice.dto.UserResponse;
import com.sadyrdas.accountmanagementservice.model.UserRole;
import com.sadyrdas.accountmanagementservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerClient(@RequestBody UserRequest userRequest){
        clientService.registerClient(userRequest);
    }

    @GetMapping("/clients")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserResponse> getAllClients(){
        return clientService.getAllClients();
    }

}
