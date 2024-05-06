package com.sadyrdas.accountmanagementservice;

import com.sadyrdas.accountmanagementservice.dto.UserRequest;
import com.sadyrdas.accountmanagementservice.dto.UserResponse;
import com.sadyrdas.accountmanagementservice.model.Client;
import com.sadyrdas.accountmanagementservice.model.User;
import com.sadyrdas.accountmanagementservice.model.UserRole;
import com.sadyrdas.accountmanagementservice.repository.UserRepository;
import com.sadyrdas.accountmanagementservice.service.ClientService;
import org.junit.*;
import org.mockito.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ClientService clientService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterClient() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test.example.com");
        userRequest.setName("Test");
        userRequest.setSurname("User");
        userRequest.setNickName("testuser");
        userRequest.setPhoneNumber("1234567890");
        userRequest.setPassword("password");
        // Set user request properties as needed for testing

        // Mock userRepository.save() method
        when(userRepository.save(any())).thenReturn(null); // Assuming save method returns void or null

        // Call the method to be tested
        clientService.registerClient(userRequest);

        // Verify that userRepository.save() was called once
        verify(userRepository, times(1)).save(any());
        // You can add more assertions based on the behavior of the method
    }

    @Test
    public void testGetAllClients() {
        // Mock userRepository.findAllByRole() method
        List<User> users = new ArrayList<>();
        User user = Client.builder()
                .name("Test")
                .surname("User")
                .email("test.example.com")
                .nickName("testuser")
                .phoneNumber("1234567890")
                .password("password")
                .role(UserRole.CLIENT)
                .build();
        users.add(user);
        // Mock the behavior of userRepository.findAllByRole()
        when(userRepository.findAllByRole(UserRole.CLIENT)).thenReturn(users);

        // Call the method to be tested
        List<UserResponse> clientResponseList = clientService.getAllClients();

        // Verify that userRepository.findAllByRole() was called once
        verify(userRepository, times(1)).findAllByRole(UserRole.CLIENT);
        // Verify that the returned list is empty
        assertEquals(1, clientResponseList.size());
        // You can add more assertions based on the behavior of the method
    }

    @Test
    public void testGetClientByEmail() {
        String email = "test@example.com";
        Client user = new Client();
        // Mock userRepository.findByEmail() method
        when(userRepository.findByEmail(email)).thenReturn(user);

        // Call the method to be tested
        User result = clientService.getClientByEmail(email);

        // Verify that userRepository.findByEmail() was called once
        verify(userRepository, times(1)).findByEmail(email);
        // Verify that the returned user is the same as the mocked one
        assertEquals(user, result);
        // You can add more assertions based on the behavior of the method
    }
}
