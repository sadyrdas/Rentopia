package com.sadyrdas.accountmanagementservice.controller;

import com.sadyrdas.accountmanagementservice.dto.UserRequest;
import com.sadyrdas.accountmanagementservice.dto.UserResponse;
import com.sadyrdas.accountmanagementservice.model.User;
import com.sadyrdas.accountmanagementservice.model.UserRole;
import com.sadyrdas.accountmanagementservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerClient(@RequestBody UserRequest userRequest){
        clientService.registerClient(userRequest);
    }

    @GetMapping("/allClients")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserResponse> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/getClientByEmail")
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponse getClientByEmail(@RequestParam String email){
        User user = clientService.getClientByEmail(email);
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        userResponse.setNickName(user.getNickName());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }
}
