package com.sadyrdas.accountmanagementservice;

import com.sadyrdas.accountmanagementservice.dto.UserRequest;
import com.sadyrdas.accountmanagementservice.dto.UserResponse;
import com.sadyrdas.accountmanagementservice.model.Admin;
import com.sadyrdas.accountmanagementservice.model.Client;
import com.sadyrdas.accountmanagementservice.model.User;
import com.sadyrdas.accountmanagementservice.model.UserRole;
import com.sadyrdas.accountmanagementservice.repository.UserRepository;
import com.sadyrdas.accountmanagementservice.service.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdminServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminService adminService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterAdmin() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test.example.com");
        userRequest.setName("Test");
        userRequest.setSurname("Admin");
        userRequest.setNickName("testAdmin");
        userRequest.setPhoneNumber("1234567890");
        userRequest.setPassword("password");
        // Set user request properties as needed for testing

        // Mock userRepository.save() method
        when(userRepository.save(any())).thenReturn(null); // Assuming save method returns void or null

        // Call the method to be tested
        adminService.registerAdmin(userRequest);

        // Verify that userRepository.save() was called once
        verify(userRepository, times(1)).save(any());
        // You can add more assertions based on the behavior of the method
    }

    @Test
    public void testGetAllAdmins() {
        // Mock userRepository.findAllByRole() method
        List<User> users = new ArrayList<>();
        User user = Admin.builder()
                .name("Test")
                .surname("Admin")
                .email("test.example.com")
                .nickName("testAdmin")
                .phoneNumber("1234567890")
                .password("password")
                .role(UserRole.ADMIN)
                .build();
        users.add(user);
        // Mock the behavior of userRepository.findAllByRole()
        when(userRepository.findAllByRole(UserRole.ADMIN)).thenReturn(users);

        // Call the method to be tested
        List<UserResponse> clientResponseList = adminService.getAllAdmins();

        // Verify that userRepository.findAllByRole() was called once
        verify(userRepository, times(1)).findAllByRole(UserRole.ADMIN);
        // Verify that the returned list is empty
        assertEquals(1, clientResponseList.size());
        // You can add more assertions based on the behavior of the method
    }

}
