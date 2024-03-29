package com.sadyrdas.equipmentmanagementservice.dto;

import com.sadyrdas.equipmentmanagementservice.model.EquipmentStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewEquipmentRequest {
    private EquipmentStatus status;
    private String title;
}
