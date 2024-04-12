package com.sadyrdas.accountmanagementservice.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("CLIENT")
@SuperBuilder
@AllArgsConstructor
public class Client extends User{
}
