package com.sadyrdas.accountmanagementservice.service;

import com.sadyrdas.accountmanagementservice.dto.UserRequest;
import com.sadyrdas.accountmanagementservice.dto.UserResponse;
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
public class AdminService extends AbstractUserService {
    private final UserRepository userRepository;

    public void registerAdmin(UserRequest userRequest) {
        registerUser(userRequest, UserRole.ADMIN);
    }

    public List<UserResponse> getAllAdmins(){
        return findAllUsersByRole(UserRole.ADMIN);
    }
}
