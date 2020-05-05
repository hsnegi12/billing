package com.setu.billing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Customer {

    @Id
    private String mobileNumber;
    private String name;

    public Customer() {

    }
}
