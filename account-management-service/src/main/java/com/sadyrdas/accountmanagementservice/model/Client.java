package com.sadyrdas.accountmanagementservice.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("CLIENT")
@SuperBuilder
@AllArgsConstructor
public class Client extends User{
}
