package com.auca.collegefeemanagement.repository;


import com.auca.collegefeemanagement.model.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface  FeePaymentRepository extends JpaRepository<FeePayment, UUID> {
    
}
