package com.sadyrdas.accountmanagementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("ADMIN")
@SuperBuilder
@AllArgsConstructor
public class Admin extends User{
}
