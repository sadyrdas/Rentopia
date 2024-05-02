package com.sadyrdas.equipmentreservationservice.controller;

import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowAddedTime;
import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowCreateRequest;
import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowResponse;
import com.sadyrdas.equipmentreservationservice.exception.ExceptionUnavailableEquipment;
import com.sadyrdas.equipmentreservationservice.service.ReservationWindowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservationWindow")
public class ReservationWindowController {
    private final ReservationWindowService reservationWindowService;

    @PostMapping("/createReservationWindow")
    public Mono<ResponseEntity<Object>> createReservationWindow(@RequestBody ReservationWindowCreateRequest reservationWindowCreateRequest) {
        return reservationWindowService.createReservationWindow(reservationWindowCreateRequest)
                .then(Mono.just(ResponseEntity.ok().build()))
                .onErrorResume(ExceptionUnavailableEquipment.class,
                        e -> Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body("Equipment is already taken or broken")));
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
