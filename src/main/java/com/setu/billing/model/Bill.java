package com.setu.billing.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Bill {
    @Id
    private String billerBillID;
    private Date generatedOn;
    private String recurrence;
    private String amountExactness;
    private String customerAccountId;
    private Double amountValue;

    public Bill() {

    }

}
