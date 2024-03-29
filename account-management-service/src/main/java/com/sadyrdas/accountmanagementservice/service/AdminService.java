package com.sadyrdas.accountmanagementservice.service;

import com.sadyrdas.accountmanagementservice.dto.UserRequest;
import com.sadyrdas.accountmanagementservice.dto.UserResponse;
import com.sadyrdas.accountmanagementservice.model.Admin;
import com.sadyrdas.accountmanagementservice.model.Client;
import com.sadyrdas.accountmanagementservice.model.User;
import com.sadyrdas.accountmanagementservice.model.UserRole;
import com.sadyrdas.accountmanagementservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final UserRepository userRepository;

    public void registerAdmin(UserRequest userRequest) {
        User user = Admin.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .nickName(userRequest.getNickName())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .role(UserRole.ADMIN)
                .build();
        userRepository.save(user);
        log.info("Admin with email {} was successfully registered", user.getEmail());
    }

    public List<UserResponse> getAllAdmins(){
        List<User> users = userRepository.findAllByRole(UserRole.ADMIN);
        List<UserResponse> adminResponseList = new ArrayList<>();
        for (User user : users){
            UserResponse userResponse = new UserResponse();
            userResponse.setName(user.getName());
            userResponse.setSurname(user.getSurname());
            userResponse.setNickName(user.getNickName());
            adminResponseList.add(userResponse);
        }
        log.info("You got all admins. Count is {}", adminResponseList.size());
        return adminResponseList;
    }
}
