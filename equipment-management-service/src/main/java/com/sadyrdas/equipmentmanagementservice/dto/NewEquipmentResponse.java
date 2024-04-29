package com.sadyrdas.equipmentmanagementservice.dto;

import com.sadyrdas.equipmentmanagementservice.model.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewEquipmentResponse {
    private String title;
    private EquipmentStatus equipmentStatus;
}
