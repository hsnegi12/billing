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
public class Receipt {

    @Id
    private String receiptId;
    private String billerBillID;
    private String platformBillID;
    private String platformTransactionRefID;
    private Date date;

    public Receipt() {

    }
}
