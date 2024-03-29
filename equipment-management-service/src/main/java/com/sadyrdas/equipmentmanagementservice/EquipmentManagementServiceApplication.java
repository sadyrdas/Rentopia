package com.sadyrdas.equipmentmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableMongoRepositories(basePackages = "com.sadyrdas.equipmentmanagementservice")
public class EquipmentManagementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EquipmentManagementServiceApplication.class, args);
    }
}
