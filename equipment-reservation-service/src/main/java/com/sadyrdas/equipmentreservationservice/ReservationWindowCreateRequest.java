package com.sadyrdas.equipmentreservationservice;

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
    private String startDate;
    private String endDate;
}
