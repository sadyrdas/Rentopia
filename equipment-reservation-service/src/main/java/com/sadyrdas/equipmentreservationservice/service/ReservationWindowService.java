package com.sadyrdas.equipmentreservationservice.service;

import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowAddedTime;
import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowCreateRequest;
import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowResponse;
import com.sadyrdas.equipmentreservationservice.model.ReservationWindow;
import com.sadyrdas.equipmentreservationservice.repository.ReservationWindowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationWindowService {
    private final ReservationWindowRepository reservationWindowRepository;
    private final WebClient.Builder webClientBuilder;

    public void createReservationWindow(ReservationWindowCreateRequest reservationWindowCreateRequest) {
        String title = reservationWindowCreateRequest.getEquipmentTitle();
        String clientEmail = reservationWindowCreateRequest.getClientEmail();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(reservationWindowCreateRequest.getStartDate(), formatter);
        LocalDateTime endDateTime = startDateTime.plusHours(reservationWindowCreateRequest.getDuration());

        //TODO Add info about client and about equipment, which were added
        webClientBuilder.build().get()
                .uri("http://equipment-management-service/api/equipment/management/getEquipmentByTitle",
                        uriBuilder -> uriBuilder
                        .queryParam("title", title)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response-> {
                    return Mono.error(new RuntimeException("Service is unavailable"));
                })
                .bodyToMono(Map.class)
                .map(response -> {
                    return (String) response.get("title");
                })
                .flatMap(equipment -> {
                    return webClientBuilder.build().get()
                            .uri(uriBuilder -> uriBuilder
                                    .scheme("http")
                                    .host("account-management-service")
                                    .path("/api/user/client/getClientByEmail")
                                    .queryParam("email", clientEmail)
                                    .build())
                            .retrieve()
                            .onStatus(HttpStatus::is5xxServerError, response-> {
                                return Mono.error(new RuntimeException("Service is unavailable"));
                            })
                            .bodyToMono(Map.class)
                            .map(response -> {
                                String email = (String) response.get("email"); // Cast is necessary
                                return Tuples.of(equipment, email);
                            });

                })
                .subscribe(resultTuple -> {
                    String equipment = resultTuple.getT1();
                    String client = resultTuple.getT2();
                    ReservationWindow reservationWindow = ReservationWindow.builder()
                            .duration(reservationWindowCreateRequest.getDuration())
                            .title(reservationWindowCreateRequest.getTitle())
                            .startDate(startDateTime)
                            .endDate(endDateTime)
                            .titleOfEquipment(equipment)
                            .clientEmail(client)
                            .build();
                    reservationWindowRepository.save(reservationWindow);
                });
    }

    public void addMeetingTime(ReservationWindowAddedTime reservationWindowAddedTime) {
        ReservationWindow reservationWindow = reservationWindowRepository.findByTitle(reservationWindowAddedTime.getTitle());
        if (reservationWindow == null) {
            log.error("Reservation window not found");
        }else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime meetingDateTime = LocalDateTime.parse(reservationWindowAddedTime.getMeetingDate(), formatter);
            reservationWindow.setMeetingDate(meetingDateTime);
            reservationWindowRepository.save(reservationWindow);
            log.info("Meeting time {} added to reservation window: {}",reservationWindow.getMeetingDate(), reservationWindow.getTitle());
        }
    }

    public ReservationWindowResponse getReservationWindowByTitle(String title) {
        ReservationWindow reservationWindow = reservationWindowRepository.findByTitle(title);
        if (reservationWindow == null) {
            log.error("Reservation window not found");
        }
        assert reservationWindow != null;
        if (reservationWindow.getMeetingDate() != null) {
            log.info("Meeting time: {}", reservationWindow.getMeetingDate());
            return ReservationWindowResponse.builder()
                    .title(reservationWindow.getTitle())
                    .startDate(reservationWindow.getStartDate().toString())
                    .endDate(reservationWindow.getEndDate().toString())
                    .equipmentTitle(reservationWindow.getTitleOfEquipment())
                    .clientEmail(reservationWindow.getClientEmail())
                    .meetingDate(reservationWindow.getMeetingDate().toString())
                    .build();
        }else {
            ReservationWindowResponse reservationWindowResponse = ReservationWindowResponse.builder()
                    .title(reservationWindow.getTitle())
                    .startDate(reservationWindow.getStartDate().toString())
                    .endDate(reservationWindow.getEndDate().toString())
                    .equipmentTitle(reservationWindow.getTitleOfEquipment())
                    .clientEmail(reservationWindow.getClientEmail())
                    .build();
            log.info("Reservation window: {}", reservationWindowResponse.getTitle());
            return reservationWindowResponse;
        }
    }
}
