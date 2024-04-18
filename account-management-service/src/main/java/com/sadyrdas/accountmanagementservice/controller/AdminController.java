package com.sadyrdas.accountmanagementservice.controller;

import com.sadyrdas.accountmanagementservice.dto.UserRequest;
import com.sadyrdas.accountmanagementservice.dto.UserResponse;
import com.sadyrdas.accountmanagementservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerAdmin(@RequestBody UserRequest userRequest){
        adminService.registerAdmin(userRequest);
    }

    @GetMapping("/allAdmins")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserResponse> getAllAdmins(){
        return adminService.getAllAdmins();
    }
}
