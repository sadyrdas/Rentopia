package com.sadyrdas.accountmanagementservice.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("Admin")
@SuperBuilder
@AllArgsConstructor
public class Admin extends User{
}
