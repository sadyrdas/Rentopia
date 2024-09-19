package com.sadyrdas.accountmanagementservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class LoginResponse {
    private String token;
    private long expiresIn;

}
