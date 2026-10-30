package com.auca.collegefeemanagement.service.service;

import com.auca.collegefeemanagement.model.PaymentStatus;
import com.auca.collegefeemanagement.model.FeePayment;
import com.auca.collegefeemanagement.repository.FeePaymentRepository;
import com.auca.collegefeemanagement.repository.ReceiptRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



@Service 

public class FeePaymentService {

    @Autowired 
   private FeePaymentRepository feePaymentRepository;

      @Autowired
    private ReceiptRepository receiptRepository;

    public String save(FeePayment payment) {
        if (payment.getPaymentDate() == null || payment.getPaymentDate().isBefore(LocalDate.now())) {
            return "Fee payment date cannot be in the past";
        }
        if (payment.getCashier() != null && feePaymentRepository.existsByCashierIdAndPaymentDateAndStatusNot(
                payment.getCashier().getId(), payment.getPaymentDate(), PaymentStatus.REVERSED)) {
            return "Cashier already has a payment booked on that date";
        }
        payment.setStatus(PaymentStatus.SUBMITTED);
        feePaymentRepository.save(payment);
        return "Fee payment saved successfully";
    }
    public List<FeePayment> getAll() {
        return feePaymentRepository.findAll();
    }

    public Optional<FeePayment> getById(UUID id) {
        return feePaymentRepository.findById(id);
    }
    public List<FeePayment> getByStatus(PaymentStatus status) {
        return feePaymentRepository.findByStatusOrderByPaymentDateAsc(status);
    }

           public List<FeePayment> getBetween(LocalDate start, LocalDate end) {
           return feePaymentRepository.findByPaymentDateBetweenOrderByPaymentDateAsc(start, end);
       }


  public String update(UUID id, FeePayment feePayment) {
        Optional<FeePayment> existing = feePaymentRepository.findById(id);
        if (existing.isEmpty()) {
            return "The fee payment with that id does not exist";
        }
        FeePayment old = existing.get();
        old.setPaymentDate(feePayment.getPaymentDate());
        old.setAcademicYear(feePayment.getAcademicYear());
        old.setAmountDue(feePayment.getAmountDue());
        old.setStudent(feePayment.getStudent());
        old.setCashier(feePayment.getCashier());
        feePaymentRepository.save(old);
        return "Fee payment updated successfully";
    }
    public String updateStatus(UUID id, PaymentStatus newStatus) {
        Optional<FeePayment> existing = feePaymentRepository.findById(id);
        if (existing.isEmpty()) {
            return "The fee payment with that id does not exist";
        }
       FeePayment payment = existing.get();
        PaymentStatus current = payment.getStatus();
        boolean allowed = false;
      if (current == PaymentStatus.SUBMITTED
                && (newStatus == PaymentStatus.VERIFIED || newStatus == PaymentStatus.REVERSED)) {
            allowed = true;
       } else if (current == PaymentStatus.VERIFIED
                && (newStatus == PaymentStatus.POSTED || newStatus == PaymentStatus.REVERSED)) {
            allowed = true;
                }
 if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        payment.setStatus(newStatus);
        feePaymentRepository.save(payment);
        return "Fee payment status updated successfully";
    }
    public String delete(UUID id) {
        Optional<FeePayment> payment = feePaymentRepository.findById(id);
        if (payment.isEmpty()) {
            return "The fee payment with that id does not exist";
        }
        if (receiptRepository.existsByFeePayment(payment.get())) {
            return "That fee payment has receipts, delete them first";
        }
        feePaymentRepository.deleteById(id);
        return "Fee payment deleted successfully";
    }
        public List<Object[]> getStatsByStatus() {
        return feePaymentRepository.countByStatus();
    }
        public String cancelDay(UUID cashierId, LocalDate date) {
        int count = feePaymentRepository.cancelDay(cashierId, date);
        return count + " fee payments cancelled";
    }

    public Page<FeePayment> getPage(Pageable pageable) {
        return feePaymentRepository.findAll(pageable);
    }
}

