package com.auca.collegefeemanagement.repository;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auca.collegefeemanagement.model.FeePayment;
import com.auca.collegefeemanagement.model.Receipt;



public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {

    @Query("SELECT d FROM Receipt d WHERE d.feePayment.student.regNumber = :regNumber ORDER BY d.issuedDate DESC")
    List<Receipt> findByStudentRegNumber(@Param("regNumber") String regNumber);
        boolean existsByFeePayment(FeePayment feePayment);
}

    

