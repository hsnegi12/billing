package com.setu.billing.service;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.util.StringUtils;

import com.setu.billing.handler.CustomerNotFoundExceptionHandler;
import com.setu.billing.model.Bill;
import com.setu.billing.model.Customer;
import com.setu.billing.repository.BillRepository;
import com.setu.billing.repository.CustomerRepository;
import com.setu.billing.utils.BillDetailsResponse;
import com.setu.billing.utils.BillRequest;
import com.setu.billing.utils.BillResponse;
import com.setu.billing.utils.Constants;
import com.setu.billing.utils.CustomerNotFoundException;
import com.setu.billing.utils.ErrorCodeFormat;

@Slf4j
public class BillService {

    public static BillDetailsResponse getBillDetailsResponse(final BillRequest billRequest, final CustomerRepository customerRepository,
                                                             final BillRepository billRepository) throws Exception{

        log.info("Fetch customer bills API request for customer with accountId: " + billRequest.getCustomerIdentifiers().getAttributeValue());
        BillRequest.CustomerIdentifiers customerIdentifiers = billRequest.getCustomerIdentifiers();
        if(Constants.MOBILE_NUMBER.equals(customerIdentifiers.getAttributeName())) {
            final String mobileNumber = customerIdentifiers.getAttributeValue();
            if(!StringUtils.isEmpty(mobileNumber)) {
                Customer customer = getCustomer(customerRepository, mobileNumber);
                if(customer == null) {
                    log.error("Customer not found in the database!");
                    throwCustomerNotFoundException();
                }
                return setBillDetailsResponse(billRepository, mobileNumber);
            }
        } else {
            log.error("Mobile number attribute cannot be empty or null!");
            throw new Exception("Invalid attribute exception");
        }
        return null;
    }

    //Returns the customer info for whom the request is received
    private static Customer getCustomer(final CustomerRepository customerRepository, final String mobileNumber) {
        List<Customer> listOfCustomers = customerRepository.findAll();
        Customer billRequestCustomer = null;
        for(Customer customer : listOfCustomers) {
            if(customer.getMobileNumber().equals(mobileNumber)) {
                billRequestCustomer = customer;
                break;
            }
        }
        return billRequestCustomer;
    }

    //Returns single bill object for a customer
    private static BillResponse getBillResponse(final Bill bill) {

        //Evaluate customer account information in Bill Response
        BillResponse.CustomerAccount customerAccount = new BillResponse.CustomerAccount();
        customerAccount.setId(bill.getCustomerAccountId());

        //Evaluate amount of bill in Bill Response
        BillResponse.Aggregates.Total.Amount amount = new BillResponse.Aggregates.Total.Amount();
        amount.setValue(bill.getAmountValue());

        //Evaluate total transaction information in Bill response
        BillResponse.Aggregates.Total total = new BillResponse.Aggregates.Total();
        total.setAmount(amount);
        total.setDisplayName(Constants.TOTAL_OUTSTANDING);

        //Aggregate above responses in aggregate entity to be set in Bill Response
        BillResponse.Aggregates aggregates = new BillResponse.Aggregates();
        aggregates.setTotal(total);

        //Set bill response
        BillResponse billResponse = new BillResponse();
        billResponse.setBillerBillID(bill.getBillerBillID());
        billResponse.setGeneratedOn(bill.getGeneratedOn());
        billResponse.setRecurrence(bill.getRecurrence());
        billResponse.setCustomerAccount(customerAccount);
        billResponse.setAggregates(aggregates);
        return billResponse;
    }

    //Return list of bill details for a customer
    private static BillDetailsResponse setBillDetailsResponse(final BillRepository billRepository, final String mobileNumber) {
        List<Bill> listOfBills = billRepository.findAll();
        boolean flag = false;
        final List<BillResponse> customerBills = new ArrayList<>();
        final BillDetailsResponse billDetailsResponse = new BillDetailsResponse();
        for(Bill bill : listOfBills) {
            if(bill.getCustomerAccountId().equals(mobileNumber)) {
                BillResponse billResponse = getBillResponse(bill);
                customerBills.add(billResponse);
                flag = true;
            }
        }
        if(flag) {
            billDetailsResponse.setBillFetchStatus(Constants.AVAILABLE);
        } else {
            billDetailsResponse.setBillFetchStatus(Constants.NO_OUTSTANDING);
        }
        billDetailsResponse.setListOfBillResponse(customerBills);
        return billDetailsResponse;
    }

    //throws exception and sets Status code as 404 along with response set as ErrorCodeFormat to be displayed
    private static void throwCustomerNotFoundException() {
        ErrorCodeFormat errorCodeFormat = new ErrorCodeFormat();
        errorCodeFormat.setCode(Constants.CUSTOMER_NOT_FOUND);
        errorCodeFormat.setTitle(Constants.CUSTOMER_NOT_FOUND_TITLE);
        errorCodeFormat.setDocURL(Constants.EMPTY);
        errorCodeFormat.setParam(Constants.EMPTY);
        errorCodeFormat.setTraceID(Constants.EMPTY);
        errorCodeFormat.setDescription(Constants.ERROR_DESCRIPTION);

        CustomerNotFoundException customerNotFoundException = new CustomerNotFoundException(errorCodeFormat);
        customerNotFoundException.setErrorCodeFormat(errorCodeFormat);

        CustomerNotFoundExceptionHandler customerNotFoundExceptionHandler = new CustomerNotFoundExceptionHandler();
        customerNotFoundExceptionHandler.handleNotFoundException(customerNotFoundException);
        throw customerNotFoundException;
    }
}
