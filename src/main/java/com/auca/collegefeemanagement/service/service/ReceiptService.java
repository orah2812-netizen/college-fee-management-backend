package com.auca.collegefeemanagement.service.service;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.collegefeemanagement.model.*;
import com.auca.collegefeemanagement.repository.FeePaymentRepository;
import com.auca.collegefeemanagement.repository.ReceiptRepository;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private FeePaymentRepository feePaymentRepository;

    public String save(UUID feePaymentId, Receipt receipt) {
        Optional<FeePayment> payment = feePaymentRepository.findById(feePaymentId);

        if (payment.isEmpty()) {
            return "The fee payment with that id does not exist";
        }
        if (payment.get().getStatus() != PaymentStatus.POSTED) {
            return "Receipts are only for POSTED fee payments";
        }
        receipt.setIssuedDate(LocalDate.now());
        receipt.setFeePayment(payment.get());
        receiptRepository.save(receipt);
        return "Receipt saved successfully";

    }
       public List<Receipt> getByStudent(String regNumber) {
        return receiptRepository.findByStudentRegNumber(regNumber);
    }
    public String delete(UUID id){
        
        if(!receiptRepository.existsById(id)) {
            return "The receipt with that id does not exist";
        }
        receiptRepository.deleteById(id);
        return "Receipt deleted successfully";
    
    }
}