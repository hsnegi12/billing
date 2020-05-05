package com.setu.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setu.billing.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
