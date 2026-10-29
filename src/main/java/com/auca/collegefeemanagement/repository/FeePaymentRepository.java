package com.auca.collegefeemanagement.repository;

import com.auca.collegefeemanagement.model.FeePayment;
import com.auca.collegefeemanagement.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface FeePaymentRepository extends JpaRepository<FeePayment, UUID> {

    List<FeePayment> findByStatusOrderByPaymentDateAsc(PaymentStatus status);
        List<FeePayment> findByPaymentDateBetweenOrderByPaymentDateAsc(LocalDate start, LocalDate end);
        
         boolean existsByCashierIdAndPaymentDateAndStatusNot(UUID cashierId, LocalDate paymentDate, PaymentStatus status);
}


