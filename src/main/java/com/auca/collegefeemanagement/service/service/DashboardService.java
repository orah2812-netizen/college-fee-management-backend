package com.auca.collegefeemanagement.service.service;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.auca.collegefeemanagement.model.PaymentStatus;
import com.auca.collegefeemanagement.repository.*;
import org.springframework.stereotype.Service;

@Service 

public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CashierRepository cashierRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private FeePaymentRepository feePaymentRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

 public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("students", studentRepository.count());
        summary.put("cashiers", cashierRepository.count());
        summary.put("freeCounters", (long) counterRepository.findFreeCounters().size());
        summary.put("paymentsToday", feePaymentRepository.countByPaymentDate(LocalDate.now()));
        summary.put("submitted", feePaymentRepository.countByStatusIs(PaymentStatus.SUBMITTED));
        summary.put("receipts", receiptRepository.count());
        return summary;
    }
}
