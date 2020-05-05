package com.setu.billing.resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setu.billing.model.Customer;
import com.setu.billing.repository.CustomerRepository;
import com.setu.billing.utils.CustomerRequest;

@Slf4j
@RestController
@RequestMapping(value = "/customer")
public class CustomerActivity {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping(value = "/add")
    public void createCustomer(@RequestBody final CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setMobileNumber(customerRequest.getMobileNumber());
        customer.setName(customerRequest.getName());
        customerRepository.save(customer);
    }
}
