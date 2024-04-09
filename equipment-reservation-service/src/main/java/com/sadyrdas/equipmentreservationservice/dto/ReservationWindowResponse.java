package com.sadyrdas.equipmentreservationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationWindowResponse {
    private String title;
    private String startDate;
    private String endDate;
    private String equipmentTitle;
    private String clientEmail;
    private String meetingDate;

}
