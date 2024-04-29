package com.sadyrdas.equipmentmanagementservice.repository;


import com.sadyrdas.equipmentmanagementservice.model.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends MongoRepository<Equipment, String> {
    @Query("{title: ?0}")
    Optional<Equipment> findByTitle(String title);

    @Query("{availability: ?0}")
    List<Equipment> getEquipmentsByAvailability(boolean availability);

    boolean existsByTitle(String title);
}
