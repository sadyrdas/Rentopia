package com.sadyrdas.equipmentreservationservice.repository;

import com.sadyrdas.equipmentreservationservice.model.ReservationWindow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationWindowRepository extends JpaRepository<ReservationWindow, Long>{
    ReservationWindow findByTitle(String title);

}
