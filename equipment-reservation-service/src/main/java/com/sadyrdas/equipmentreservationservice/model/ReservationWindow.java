package com.sadyrdas.equipmentreservationservice.model;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationWindowId;
    private String title;
    private int duration;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime meetingDate;
    private String titleOfEquipment;
    private String clientEmail;
    //TODO Add fields for client and equipment after adding WebClient
}
