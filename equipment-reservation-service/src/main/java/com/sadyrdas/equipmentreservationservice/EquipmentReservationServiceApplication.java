package com.sadyrdas.equipmentreservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EquipmentReservationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EquipmentReservationServiceApplication.class, args);
    }
}
