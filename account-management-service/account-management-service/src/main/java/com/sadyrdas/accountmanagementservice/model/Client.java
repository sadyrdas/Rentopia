package com.sadyrdas.accountmanagementservice.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Client")
public class Client extends User{
}
