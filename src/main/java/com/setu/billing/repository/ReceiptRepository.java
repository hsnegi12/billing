package com.setu.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setu.billing.model.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
}
