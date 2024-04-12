package com.sadyrdas.equipmentmanagementservice.model;

import javax.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document("equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Field("status")
    private EquipmentStatus status;
    @Field("availability")
    private boolean availability = true;
    @Field("title")
    private String title;
    @Field("description")
    private String description;
}
