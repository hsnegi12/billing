package com.setu.billing.service;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import com.setu.billing.repository.ReceiptRepository;
import com.setu.billing.utils.ReceiptRequest;
import com.setu.billing.utils.ReceiptResponse;

@Slf4j
public class ReceiptService {

    public static ReceiptResponse getReceiptFromBillAndReferenceId(final ReceiptRequest receiptRequest, final ReceiptRepository receiptRepository) {

        log.info("Fetch receipt request for billId: " + receiptRequest.getBillerBillID() +
                " and transaction reference Id: " + receiptRequest.getPaymentDetails().getPlatformTransactionRefID());

        com.setu.billing.model.Receipt receiptEntity = getReceiptEntity(receiptRequest);
        ReceiptResponse receiptResponse = getReceiptResponse(receiptRequest);

        ReceiptResponse.Receipt receipt = new ReceiptResponse.Receipt();
        String receiptId = getReceiptId(receiptRequest.getBillerBillID(), receiptRequest.getPaymentDetails().getPlatformTransactionRefID());
        receipt.setId(receiptId);
        receiptResponse.setReceipt(receipt);
        receiptEntity.setReceiptId(receiptId);

        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        receipt.setDate(date);
        receiptEntity.setDate(date);

        //save the receipt record to DB
        receiptRepository.save(receiptEntity);
        log.info("Receipt successfully saved to DB with receiptId: " + receiptEntity.getReceiptId());
        return receiptResponse;
    }

    //Receipt entity to be saved in DB
    private static com.setu.billing.model.Receipt getReceiptEntity(final ReceiptRequest receiptRequest) {
        com.setu.billing.model.Receipt receiptDB = new com.setu.billing.model.Receipt();
        receiptDB.setBillerBillID(receiptRequest.getBillerBillID());
        receiptDB.setPlatformBillID(receiptRequest.getPlatformBillID());
        receiptDB.setPlatformTransactionRefID(receiptRequest.getPaymentDetails().getPlatformTransactionRefID());
        return receiptDB;
    }

    //Receipt response to be sent back as API response
    private static ReceiptResponse getReceiptResponse(final ReceiptRequest receiptRequest) {
        ReceiptResponse receiptResponse = new ReceiptResponse();
        receiptResponse.setBillerBillID(receiptRequest.getBillerBillID());
        receiptResponse.setPlatformBillID(receiptRequest.getPlatformBillID());
        receiptResponse.setPlatformTransactionRefID(receiptRequest.getPaymentDetails().getPlatformTransactionRefID());
        return receiptResponse;
    }

    //making unique for combination of billID & platformTransactionRefID as per requirement
    private static String getReceiptId(final String billId, final String transactionRefId) {
        return "R" + billId + "_" + transactionRefId;
    }
}
