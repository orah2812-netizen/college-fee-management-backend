package com.auca.collegefeemanagement.service.service;

import com.auca.collegefeemanagement.model.FeePayment;
import com.auca.collegefeemanagement.repository.FeePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 

public class FeePaymentService {

    @Autowired 
   private FeePaymentRepository feePaymentRepository;
public String save(FeePayment feePayment) {
        feePaymentRepository.save(feePayment);
        return "Fee payment saved successfully";
    }

    public List<FeePayment> getAll() {
        return feePaymentRepository.findAll();
    }

    public Optional<FeePayment> getById(UUID id) {
        return feePaymentRepository.findById(id);
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

     public String delete(UUID id) {
        if (!feePaymentRepository.existsById(id)) {
            return "The fee payment with that id does not exist";
        }
        feePaymentRepository.deleteById(id);
        return "Fee payment deleted successfully";
    }
}

