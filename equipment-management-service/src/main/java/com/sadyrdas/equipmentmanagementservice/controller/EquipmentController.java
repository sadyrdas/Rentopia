package com.sadyrdas.equipmentmanagementservice.controller;

import com.sadyrdas.equipmentmanagementservice.dto.DescriptionRequest;
import com.sadyrdas.equipmentmanagementservice.dto.NewEquipmentRequest;
import com.sadyrdas.equipmentmanagementservice.dto.NewEquipmentResponse;
import com.sadyrdas.equipmentmanagementservice.dto.UpdateStatusEquipment;
import com.sadyrdas.equipmentmanagementservice.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment/management")
@RequiredArgsConstructor
@Slf4j
public class EquipmentController {
    private final EquipmentService equipmentService;

    @PostMapping("/newEquipment")
    @ResponseStatus(value = HttpStatus.CREATED)
    public NewEquipmentResponse addEquipment(@RequestBody NewEquipmentRequest equipment) {
        equipmentService.addEquipment(equipment);
        return new NewEquipmentResponse(equipment.getTitle(), equipment.getStatus());
    }

    @GetMapping("/allEquipments")
    @ResponseStatus(value = HttpStatus.OK)
    public List<NewEquipmentResponse> getAllEquipments(){
        return equipmentService.getAllEquipment();
    }

    @PutMapping("/makeEquipmentAvailable")
    @ResponseStatus(value = HttpStatus.OK)
    public void makeEquipmentAvailable(@RequestBody UpdateStatusEquipment updateStatusEquipment, @RequestParam boolean availability){
        equipmentService.updateStatusOfEquipment(updateStatusEquipment, availability);
    }

    @PostMapping("/descriptionToEquipment")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> addDescriptionToEquipment(@RequestBody DescriptionRequest descriptionRequest){
        try {
            equipmentService.addDescriptionToEquipment(descriptionRequest.getTitle(), descriptionRequest.getDescription());
            return ResponseEntity.ok("Description was added");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Equipment is not found");
        }
    }

    @DeleteMapping("/deleteEquipment")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEquipment(@RequestBody UpdateStatusEquipment updateStatusEquipment){
        equipmentService.removeEquipment(updateStatusEquipment.getTitle());
    }

}
