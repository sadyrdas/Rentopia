package com.sadyrdas.equipmentreservationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservationWindow")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReservationWindow {
    @Id
    private Long reservationWindowId;
    private String title;
    private int duration;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String meetingDate;
    //TODO Add fields for client and equipment after adding WebClient
}
