package com.sadyrdas.equipmentmanagementservice.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.sadyrdas.equipmentmanagementservice.repository")
public class MongoConfig {
}
