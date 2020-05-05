package com.setu.billing.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptRequest {

    private String billerBillID;
    private String platformBillID;
    private PaymentDetails paymentDetails;

    @Setter
    @Getter
    public static class PaymentDetails {
        private String platformTransactionRefID;
        private String uniquePaymentRefID;
        private AmountPaid amountPaid;
        private BillAmount billAmount;

        @Getter
        @Setter
        public static class AmountPaid {
            private double value;
        }

        @Getter
        @Setter
        public static class BillAmount {
            private double value;
        }
    }
}
