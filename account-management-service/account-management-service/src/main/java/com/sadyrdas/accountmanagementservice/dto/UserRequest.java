package com.sadyrdas.accountmanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String email;
    private String name;
    private String surname;
    private String nickName;
    private String phoneNumber;
    private String password;
}
