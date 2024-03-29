package com.sadyrdas.equipmentmanagementservice.dto;

import com.sadyrdas.equipmentmanagementservice.model.EquipmentStatus;
import lombok.Data;


@Data
public class UpdateStatusEquipment {
    private String title;
    private EquipmentStatus equipmentStatus;
}
