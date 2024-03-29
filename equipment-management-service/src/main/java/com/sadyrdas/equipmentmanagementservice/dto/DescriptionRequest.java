package com.sadyrdas.equipmentmanagementservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionRequest {
    private String title;
    private String description;
}
