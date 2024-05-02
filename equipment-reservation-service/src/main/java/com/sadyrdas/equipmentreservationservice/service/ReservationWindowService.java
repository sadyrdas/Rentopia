package com.sadyrdas.equipmentreservationservice.service;

import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowAddedTime;
import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowCreateRequest;
import com.sadyrdas.equipmentreservationservice.dto.ReservationWindowResponse;
import com.sadyrdas.equipmentreservationservice.exception.ExceptionUnavailableEquipment;
import com.sadyrdas.equipmentreservationservice.model.ReservationWindow;
import com.sadyrdas.equipmentreservationservice.repository.ReservationWindowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public Mono<Void> createReservationWindow(ReservationWindowCreateRequest reservationWindowCreateRequest) {
        String title = reservationWindowCreateRequest.getEquipmentTitle();
        String clientEmail = reservationWindowCreateRequest.getClientEmail();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(reservationWindowCreateRequest.getStartDate(), formatter);
        LocalDateTime endDateTime = startDateTime.plusHours(reservationWindowCreateRequest.getDuration());

        return webClientBuilder.build().get()
                .uri("http://equipment-management-service/api/equipment/management/getEquipmentByTitle",
                        uriBuilder -> uriBuilder
                                .queryParam("title", title)
                                .build())
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(equipmentResponse -> {
                    String equipmentStatus = (String) equipmentResponse.get("equipmentStatus");

                    if (equipmentStatus != null && equipmentStatus.equals("TAKEN") || equipmentStatus.equals("BROKEN")) {
                        return Mono.error(new ExceptionUnavailableEquipment("Equipment is already taken or broken"));
                    } else {
                        return webClientBuilder.build().get()
                                .uri("http://account-management-service/api/client/getClientByEmail",
                                        uriBuilder -> uriBuilder
                                                .queryParam("email", clientEmail)
                                                .build())
                                .retrieve()
                                .bodyToMono(Map.class)
                                .map(clientResponse -> Tuples.of(clientResponse.get("email"), equipmentResponse));
                    }
                })
                .flatMap(resultTuple -> {
                    String client = (String) resultTuple.getT1();
                    Map<String, Object> equipmentResponse = resultTuple.getT2();
                    String equipment = (String) equipmentResponse.get("title");

                    ReservationWindow reservationWindow = ReservationWindow.builder()
                            .duration(reservationWindowCreateRequest.getDuration())
                            .title(reservationWindowCreateRequest.getTitle())
                            .startDate(startDateTime)
                            .endDate(endDateTime)
                            .titleOfEquipment(equipment)
                            .clientEmail(client)
                            .build();

                    return Mono.just(reservationWindowRepository.save(reservationWindow)).then(Mono.empty());
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
