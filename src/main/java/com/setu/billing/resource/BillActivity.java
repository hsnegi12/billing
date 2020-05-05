package com.setu.billing.resource;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setu.billing.model.Bill;
import com.setu.billing.repository.BillRepository;
import com.setu.billing.repository.CustomerRepository;
import com.setu.billing.service.AuthorizationService;
import com.setu.billing.service.BillService;
import com.setu.billing.utils.BillDetailsResponse;
import com.setu.billing.utils.BillRequest;

@Slf4j
@RestController
@RequestMapping(value = "/bill")
public class BillActivity {

    @Autowired
    BillRepository billRepository;

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping(value = "/add")
    public void persistBillToDB(@RequestBody final Bill bill) {

        log.info("Bill generated for user with accountId: " + bill.getCustomerAccountId() + " with billId: " + bill.getBillerBillID());

        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        bill.setGeneratedOn(date);
        billRepository.save(bill);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<BillDetailsResponse> getCustomerBills(@NotNull @RequestBody final BillRequest billRequest,
                                                                @NotNull @RequestHeader(("Authorization")) String auth) throws Exception {

        AuthorizationService.verifyBearerToken(auth);
        log.info("Token authorized for fetch customer bills API with auth token :" + auth);


        return new ResponseEntity<>(BillService.getBillDetailsResponse(billRequest, customerRepository, billRepository), HttpStatus.OK);
    }
}
