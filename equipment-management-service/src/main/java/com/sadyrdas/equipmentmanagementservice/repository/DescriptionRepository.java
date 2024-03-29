package com.sadyrdas.equipmentmanagementservice.repository;

import com.sadyrdas.equipmentmanagementservice.model.Description;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DescriptionRepository extends MongoRepository<Description, String> {
}
