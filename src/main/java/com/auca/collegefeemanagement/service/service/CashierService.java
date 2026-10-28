package com.auca.collegefeemanagement.service.service;


import com.auca.collegefeemanagement.model.Cashier;
import com.auca.collegefeemanagement.repository.CashierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service


public class CashierService {
    
    @Autowired 
    public CashierRepository cashierRepository;
    public String save(Cashier cashier) {
        cashierRepository.save(cashier);
        return "Cashier saved successfully";
    }
    public List<Cashier> getAll() {
        return cashierRepository.findAll();
    }
    public Optional<Cashier> getById(UUID id) {
        return cashierRepository.findById(id);
    }

    public String update(UUID id, Cashier cashier) {
        Optional<Cashier> existing = cashierRepository.findById(id);
        if (existing.isEmpty()) {
            return "The cashier with that id does not exist";
        }
        Cashier old = existing.get();
        old.setFirstName(cashier.getFirstName());
        old.setLastName(cashier.getLastName());
        old.setStaffNumber(cashier.getStaffNumber());
        old.setCounter(cashier.getCounter());
        old.setFeeTypes(cashier.getFeeTypes());
        cashierRepository.save(old);
        return "Cashier updated successfully";
    }
    public String delete(UUID id) {
        if (!cashierRepository.existsById(id)) {
            return "The cashier with that id does not exist";
        }
        cashierRepository.deleteById(id);
        return "Cashier deleted successfully";
    }
}
