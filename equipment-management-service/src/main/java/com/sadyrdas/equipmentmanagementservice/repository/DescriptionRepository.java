package com.sadyrdas.equipmentmanagementservice.repository;

import com.sadyrdas.equipmentmanagementservice.model.Description;
import com.sadyrdas.equipmentmanagementservice.model.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;


public interface DescriptionRepository extends MongoRepository<Description, String> {
    @Query("{equipmentTitle: ?0}")
    Optional<Description> findByEquipmentTitle(String title);
}
