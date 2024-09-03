package com.sadyrdas.accountmanagementservice.service;

import com.sadyrdas.accountmanagementservice.dto.UserRequest;
import com.sadyrdas.accountmanagementservice.dto.UserResponse;
import com.sadyrdas.accountmanagementservice.exception.EmailAlreadyExists;
import com.sadyrdas.accountmanagementservice.exception.UserNotFoundException;
import com.sadyrdas.accountmanagementservice.model.Client;
import com.sadyrdas.accountmanagementservice.model.User;
import com.sadyrdas.accountmanagementservice.model.UserRole;
import com.sadyrdas.accountmanagementservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class ClientService {

    private final UserRepository userRepository;
    public void registerClient(@Valid UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())){
            log.error("Client with email {} already exists", userRequest.getEmail());
            throw new EmailAlreadyExists("Client with email " + userRequest.getEmail() + " already exists");
        }
        User user = Client.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .nickName(userRequest.getNickName())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .role(UserRole.CLIENT)
                .build();
        userRepository.save(user);
        log.info("Client with email {} was successfully registered", user.getEmail());
    }

    public List<UserResponse> getAllClients() {
        List<User> users = userRepository.findAllByRole(UserRole.CLIENT);
        List<UserResponse> clientResponseList = new ArrayList<>();
        for (User user : users){
            UserResponse userResponse = new UserResponse();
            userResponse.setName(user.getName());
            userResponse.setSurname(user.getSurname());
            userResponse.setNickName(user.getNickName());
            userResponse.setEmail(user.getEmail());
            clientResponseList.add(userResponse);
        }
        log.info("You got all clients. Count is {}", clientResponseList.size());
        return clientResponseList;
    }
}
