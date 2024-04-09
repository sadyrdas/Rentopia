package com.sadyrdas.equipmentreservationservice.controller;

import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowAddedTime;
import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowCreateRequest;
import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowResponse;
import com.sadyrdas.equipmentreservationservice.service.ReservationWindowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservationWindow")
public class ReservationWindowController {
    private final ReservationWindowService reservationWindowService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createReservationWindow")
    public void createReservationWindow(@RequestBody ReservationWindowCreateRequest reservationWindowCreateRequest) {
        reservationWindowService.createReservationWindow(reservationWindowCreateRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getReservationWindow")
    public ReservationWindowResponse getReservationWindow(@RequestParam String title) {
        return reservationWindowService.getReservationWindowByTitle(title);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/addMeetingTime")
    public ResponseEntity<ReservationWindowAddedTime> addMeetingTime(@RequestBody ReservationWindowAddedTime reservationWindowAddedTime) {
        reservationWindowService.addMeetingTime(reservationWindowAddedTime);
        return ResponseEntity.ok(reservationWindowAddedTime);
    }
}
