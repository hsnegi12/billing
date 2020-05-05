package com.setu.billing.utils;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillResponse {
    private String billerBillID;
    private Date generatedOn;
    private String recurrence;
    private String amountExactness;
    private CustomerAccount customerAccount;
    private Aggregates aggregates;


    @Setter
    @Getter
    public static class CustomerAccount {
        private String id;
    }

    @Setter
    @Getter
    public static class Aggregates {

        private Total total;

        @Setter
        @Getter
        public static class Total {

            private String displayName;
            private Amount amount;

            @Setter
            @Getter
            public static class Amount {
                private Double value;
            }
        }
    }
}
