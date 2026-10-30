package com.auca.collegefeemanagement.repository;

import com.auca.collegefeemanagement.model.FeePayment;
import com.auca.collegefeemanagement.model.PaymentStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.*;

import jakarta.transaction.Transactional;

public interface FeePaymentRepository extends JpaRepository<FeePayment, UUID> {

    List<FeePayment> findByStatusOrderByPaymentDateAsc(PaymentStatus status);
        List<FeePayment> findByPaymentDateBetweenOrderByPaymentDateAsc(LocalDate start, LocalDate end);

         boolean existsByCashierIdAndPaymentDateAndStatusNot(UUID cashierId, LocalDate paymentDate, PaymentStatus status);

             @Query("SELECT p.status, COUNT(p) FROM FeePayment p GROUP BY p.status")
    List<Object[]> countByStatus();

        @Modifying
    @Transactional
    @Query("UPDATE FeePayment p SET p.status = com.auca.collegefeemanagement.model.PaymentStatus.REVERSED "
         + "WHERE p.cashier.id = :cashierId AND p.paymentDate = :date "
         + "AND p.status <> com.auca.collegefeemanagement.model.PaymentStatus.POSTED")
    int cancelDay(@Param("cashierId") UUID cashierId, @Param("date") LocalDate date);

    long countByPaymentDate(LocalDate PaymentDate);
    
     long countByStatusIs(PaymentStatus status);
}


