package com.setu.billing.resource;

import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setu.billing.repository.ReceiptRepository;
import com.setu.billing.service.AuthorizationService;
import com.setu.billing.service.ReceiptService;
import com.setu.billing.utils.ReceiptRequest;
import com.setu.billing.utils.ReceiptResponse;

@Slf4j
@RestController
@RequestMapping(value = "/receipt")
public class ReceiptActivity {

    @Autowired
    ReceiptRepository receiptRepository;

    @GetMapping(value = "/get")
    public ResponseEntity<ReceiptResponse> getReceipt(@NotNull @RequestBody final ReceiptRequest receiptRequest,
                                                      @NotNull @RequestHeader(("Authorization")) String auth) {

        AuthorizationService.verifyBearerToken(auth);
        log.info("Token authorized for fetch receipt API with auth token :" + auth);

        return new ResponseEntity<>(ReceiptService.getReceiptFromBillAndReferenceId(receiptRequest, receiptRepository),
                HttpStatus.OK);
    }

}
