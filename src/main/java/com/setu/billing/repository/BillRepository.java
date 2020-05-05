package com.setu.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setu.billing.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {
}
