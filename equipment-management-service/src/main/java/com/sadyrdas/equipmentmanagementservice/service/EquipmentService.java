package com.sadyrdas.equipmentmanagementservice.service;

import com.sadyrdas.equipmentmanagementservice.dto.NewEquipmentRequest;
import com.sadyrdas.equipmentmanagementservice.dto.NewEquipmentResponse;
import com.sadyrdas.equipmentmanagementservice.dto.UpdateStatusEquipment;
import com.sadyrdas.equipmentmanagementservice.model.Description;
import com.sadyrdas.equipmentmanagementservice.model.Equipment;
import com.sadyrdas.equipmentmanagementservice.model.EquipmentStatus;
import com.sadyrdas.equipmentmanagementservice.repository.DescriptionRepository;
import com.sadyrdas.equipmentmanagementservice.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final DescriptionRepository descriptionRepository;

    private boolean checkIfEquipmentExists(String title) {
        return equipmentRepository.existsByTitle(title);
    }

    public void addEquipment(NewEquipmentRequest newEquipmentRequest) {
        if (!checkIfEquipmentExists(newEquipmentRequest.getTitle())){
            Equipment equipment = Equipment.builder()
                    .title(newEquipmentRequest.getTitle())
                    .status(EquipmentStatus.IMMACULATE)
                    .description("null")
                    .build();
            log.info("Equipment {} was added", equipment.getTitle());
            equipmentRepository.insert(equipment);
        }else {
            log.info("Equipment {} already exists", newEquipmentRequest.getTitle());
        }
    }

    public void updateStatusOfEquipment(UpdateStatusEquipment updateStatusEquipment, boolean availability){
        Equipment savedEquipment = equipmentRepository.findByTitle(updateStatusEquipment.getTitle())
                .orElseThrow(() -> {
                    String errorMessage = "Cannot find equipment with id " + updateStatusEquipment.getTitle();
                    log.error(errorMessage);
                    return new RuntimeException(errorMessage);
                });
        if (updateStatusEquipment.getEquipmentStatus() == EquipmentStatus.BROKEN || updateStatusEquipment.getEquipmentStatus() == EquipmentStatus.TAKEN){
            savedEquipment.setAvailability(availability);
            savedEquipment.setStatus(updateStatusEquipment.getEquipmentStatus());
            log.info("Equipment {} is not ready for use", updateStatusEquipment.getTitle());
        }else {
            savedEquipment.setAvailability(availability);
            savedEquipment.setStatus(updateStatusEquipment.getEquipmentStatus());
            log.info("Equipment {} is ready for use", updateStatusEquipment.getTitle());
        }
        equipmentRepository.save(savedEquipment);
    }

    public List<NewEquipmentResponse> getAllEquipment(){
        List<Equipment> equipmentList = equipmentRepository.findAll();
        List<NewEquipmentResponse> newEquipmentRespons = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            NewEquipmentResponse newEquipmentResponse = new NewEquipmentResponse();
            newEquipmentResponse.setTitle(equipment.getTitle());
            newEquipmentResponse.setEquipmentStatus(equipment.getStatus());
            newEquipmentRespons.add(newEquipmentResponse);
        }
        return newEquipmentRespons;
    }

    public NewEquipmentResponse getEquipmentByTitle(String title){
        Optional<Equipment> equipment = equipmentRepository.findByTitle(title);
        if (equipment.isEmpty()) {
            log.error("Equipment {} not found", title);
        }
        NewEquipmentResponse newEquipmentResponse = new NewEquipmentResponse();
        newEquipmentResponse.setTitle(equipment.get().getTitle());
        newEquipmentResponse.setEquipmentStatus(equipment.get().getStatus());
        return newEquipmentResponse;
    }


    public void addDescriptionToEquipment(String title, String descriptionInfo){
        Optional<Equipment> equipment = equipmentRepository.findByTitle(title.trim());
        if (equipment.isPresent()){
            if (Objects.equals(equipment.get().getDescription(), "null")){
                Description description = new Description();
                description.setInfo(descriptionInfo);
                description.setLastUpdatedDate(LocalDateTime.now());
                description.setEquipmentTitle(title);
                equipment.get().setDescription(descriptionInfo);
                descriptionRepository.insert(description);
                equipmentRepository.save(equipment.get());
            }else {
                log.error("Description already exists");
            }
        }else {
            log.error("Equipment {} not found", title);
        }
    }

    public void removeEquipment(String title){
        Optional<Equipment> equipment = equipmentRepository.findByTitle(title);
        Optional<Description> description = descriptionRepository.findByEquipmentTitle(title);
        if (equipment.isPresent() || description.isPresent()){
            equipmentRepository.delete(equipment.get());
            descriptionRepository.delete(description.get());
            log.info("Equipment {} was removed", title);
        }else {
            log.error("Equipment {} not found", title);
        }
    }

    public List<NewEquipmentResponse> getAvailableEquipments(boolean availability){
        List<Equipment> equipmentList = equipmentRepository.getEquipmentsByAvailability(availability);
        List<NewEquipmentResponse> equipmentResponseList = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            equipmentResponseList.add(NewEquipmentResponse.builder()
                    .title(equipment.getTitle())
                    .equipmentStatus(equipment.getStatus())
                    .build());
            log.info("Equipment {} is available", equipment.getTitle());
        }
        return equipmentResponseList;
    }
}
