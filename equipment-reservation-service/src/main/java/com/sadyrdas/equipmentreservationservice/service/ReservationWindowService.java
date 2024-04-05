package com.sadyrdas.equipmentreservationservice.service;

import com.sadyrdas.equipmentreservationservice.ReservationWindowCreateRequest;
import com.sadyrdas.equipmentreservationservice.model.ReservationWindow;
import com.sadyrdas.equipmentreservationservice.repository.ReservationWindowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationWindowService {
    private final ReservationWindowRepository reservationWindowRepository;

    public void createReservationWindow(ReservationWindowCreateRequest reservationWindowCreateRequest) {
        ReservationWindow reservationWindow = ReservationWindow.builder()
                .duration(reservationWindowCreateRequest.getDuration())
                .startDate(LocalDateTime.parse(reservationWindowCreateRequest.getStartDate()))
                .endDate(LocalDateTime.parse(reservationWindowCreateRequest.getEndDate()))
                .build();

        //TODO Add info about client and about equipment, which were added

        reservationWindowRepository.save(reservationWindow);
    }

    public void addMeetingTime(String titleOfResWindow, String meetingDate) {
        ReservationWindow reservationWindow = reservationWindowRepository.findByTitle(titleOfResWindow);
        if (reservationWindow == null) {
            log.error("Reservation window not found");
        }else {
            reservationWindow.setMeetingDate(meetingDate);
            reservationWindowRepository.save(reservationWindow);
        }
    }
}
