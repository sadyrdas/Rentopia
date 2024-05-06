package com.sadyrdas.equipmentmanagementservice;

import com.sadyrdas.equipmentmanagementservice.dto.NewEquipmentRequest;
import com.sadyrdas.equipmentmanagementservice.dto.NewEquipmentResponse;
import com.sadyrdas.equipmentmanagementservice.dto.UpdateStatusEquipment;
import com.sadyrdas.equipmentmanagementservice.model.Description;
import com.sadyrdas.equipmentmanagementservice.model.Equipment;
import com.sadyrdas.equipmentmanagementservice.model.EquipmentStatus;
import com.sadyrdas.equipmentmanagementservice.repository.DescriptionRepository;
import com.sadyrdas.equipmentmanagementservice.repository.EquipmentRepository;
import com.sadyrdas.equipmentmanagementservice.service.EquipmentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private DescriptionRepository descriptionRepository;

    @InjectMocks
    private EquipmentService equipmentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddEquipment() {
        NewEquipmentRequest newEquipmentRequest = new NewEquipmentRequest();
        newEquipmentRequest.setTitle("Test Equipment");
        // Set other properties as needed

        // Mock behavior of equipmentRepository.existsByTitle()
        when(equipmentRepository.existsByTitle("Test Equipment")).thenReturn(false);

        // Call the method to be tested
        equipmentService.addEquipment(newEquipmentRequest);

        // Verify that equipmentRepository.insert() was called once
        verify(equipmentRepository, times(1)).insert((Equipment) any());
        // You can add more assertions based on the behavior of the method
    }

    @Test
    public void testUpdateStatusOfEquipment() {
        UpdateStatusEquipment updateStatusEquipment = new UpdateStatusEquipment();
        updateStatusEquipment.setTitle("Test Equipment");
        updateStatusEquipment.setEquipmentStatus(EquipmentStatus.IMMACULATE);

        Equipment equipment = new Equipment();
        equipment.setTitle("Test Equipment");

        when(equipmentRepository.findByTitle("Test Equipment")).thenReturn(Optional.of(equipment));
        when(equipmentRepository.save(any())).thenReturn(null);

        equipmentService.updateStatusOfEquipment(updateStatusEquipment, true);

        verify(equipmentRepository, times(1)).save(any());
    }

    @Test
    public void testGetAllEquipment() {
        // Mock data
        Equipment equipment = new Equipment();
        equipment.setTitle("Test Equipment");
        equipment.setStatus(EquipmentStatus.IMMACULATE);

        when(equipmentRepository.findAll()).thenReturn(List.of(equipment));

        // Call the method to be tested
        List<NewEquipmentResponse> result = equipmentService.getAllEquipment();

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("Test Equipment", result.get(0).getTitle());
        assertEquals(EquipmentStatus.IMMACULATE, result.get(0).getEquipmentStatus());
    }

    @Test
    public void testGetEquipmentByTitle() {
        Equipment equipment = new Equipment();
        equipment.setTitle("Test Equipment");
        equipment.setStatus(EquipmentStatus.IMMACULATE);

        when(equipmentRepository.findByTitle("Test Equipment")).thenReturn(Optional.of(equipment));

        NewEquipmentResponse result = equipmentService.getEquipmentByTitle("Test Equipment");

        assertEquals("Test Equipment", result.getTitle());
        assertEquals(EquipmentStatus.IMMACULATE, result.getEquipmentStatus());
    }

    @Test
    public void testAddDescriptionToEquipment_DescriptionAlreadyExists() {
        when(equipmentRepository.findByTitle("Test Equipment")).thenReturn(Optional.of(new Equipment()));
        when(descriptionRepository.findByEquipmentTitle("Test Equipment")).thenReturn(Optional.of(new Description()));

        equipmentService.addDescriptionToEquipment("Test Equipment", "Test Description");

        verify(equipmentRepository, never()).save(any());
        verify(descriptionRepository, never()).insert((Description) any());
    }


    @Test
    public void testRemoveEquipment() {
        Equipment equipment = new Equipment();
        equipment.setTitle("Test Equipment");

        Description description = new Description();
        description.setEquipmentTitle("Test Equipment");

        when(equipmentRepository.findByTitle("Test Equipment")).thenReturn(Optional.of(equipment));
        when(descriptionRepository.findByEquipmentTitle("Test Equipment")).thenReturn(Optional.of(description));

        equipmentService.removeEquipment("Test Equipment");

        verify(equipmentRepository, times(1)).delete(equipment);
        verify(descriptionRepository, times(1)).delete(description);
    }

    @Test
    public void testGetAvailableEquipments() {
        Equipment equipment = new Equipment();
        equipment.setTitle("Test Equipment");
        equipment.setStatus(EquipmentStatus.IMMACULATE);

        when(equipmentRepository.getEquipmentsByAvailability(true)).thenReturn(List.of(equipment));

        List<NewEquipmentResponse> result = equipmentService.getAvailableEquipments(true);

        assertEquals(1, result.size());
        assertEquals("Test Equipment", result.get(0).getTitle());
        assertEquals(EquipmentStatus.IMMACULATE, result.get(0).getEquipmentStatus());
    }
}
