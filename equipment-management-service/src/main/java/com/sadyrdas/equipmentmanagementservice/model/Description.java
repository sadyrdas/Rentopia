package com.sadyrdas.equipmentmanagementservice.model;

import javax.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document("description")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Field("equipmentTitle")
    private String equipmentTitle;
    @Field("info")
    private String info;
    @Field("dateOfUpdate")
    private LocalDateTime lastUpdatedDate;
}
