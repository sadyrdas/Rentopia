package com.sadyrdas.accountmanagementservice.service;

import com.sadyrdas.accountmanagementservice.dto.UserRequest;
import com.sadyrdas.accountmanagementservice.dto.UserResponse;
import com.sadyrdas.accountmanagementservice.model.User;
import com.sadyrdas.accountmanagementservice.model.UserRole;
import com.sadyrdas.accountmanagementservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractUserService {
    protected UserRepository userRepository;

    protected void registerUser(UserRequest userRequest, UserRole role) {
        User user = User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .nickName(userRequest.getNickName())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .role(role)
                .build();
        userRepository.save(user);
        log.info("User with email {} was successfully registered", user.getEmail());
    }
    protected List<UserResponse> findAllUsersByRole(UserRole userRole) {
        List<User> users = userRepository.findAllByRole(userRole);
        List<UserResponse> clientResponseList = new ArrayList<UserResponse>();
        for (User user : users){
            UserResponse userResponse = new UserResponse();
            userResponse.setName(user.getName());
            userResponse.setSurname(user.getSurname());
            userResponse.setNickName(user.getNickName());
            clientResponseList.add(userResponse);
        }
        return clientResponseList;
    }

}
