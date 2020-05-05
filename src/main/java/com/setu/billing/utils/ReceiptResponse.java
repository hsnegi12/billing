package com.setu.billing.utils;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptResponse {
    private String billerBillID;
    private String platformBillID;
    private String platformTransactionRefID;
    private Receipt receipt;

    @Getter
    @Setter
    public static class Receipt {
        private String id;
        private Date date;
    }
}
