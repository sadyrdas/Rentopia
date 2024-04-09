package com.sadyrdas.equipmentreservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationWindowCreateRequest {
    private int duration;
    private String title;
    private String startDate;
    private String endDate;
    private String equipmentTitle;
    private String clientEmail;
}
