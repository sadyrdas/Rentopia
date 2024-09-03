package com.sadyrdas.accountmanagementservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "Surname is mandatory")
    private String surname;
    @Column(nullable = false)
    @NotBlank(message = "Nick name is mandatory")
    private String nickName;

    @Pattern(regexp = "\\+420\\d{9}", message = "Phone number must be in format +420xxxxxxxxx")
    private String phoneNumber;

    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;
}
