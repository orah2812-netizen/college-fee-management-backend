package com.auca.collegefeemanagement.service.service;


import com.auca.collegefeemanagement.model.Cashier;
import com.auca.collegefeemanagement.model.FeeType;
import com.auca.collegefeemanagement.repository.CashierRepository;
import com.auca.collegefeemanagement.repository.CounterRepository;
import com.auca.collegefeemanagement.repository.FeeTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.auca.collegefeemanagement.model.Counter;


@Service


public class CashierService {
    
    @Autowired 
    public CashierRepository cashierRepository;
    @Autowired


    private FeeTypeRepository feeTypeRepository;

    @Autowired 
    private  CounterRepository counterRepository;

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

        public List<Cashier> getByFeeType(String name) {
        return cashierRepository.searchByFeeTypeName(name);
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
            public String addFeeType(UUID cashierId, UUID feeTypeId) {
        Optional<Cashier> cashier = cashierRepository.findById(cashierId);
        if (cashier.isEmpty()) {
            return "The cashier with that id does not exist";
        }
        Optional<FeeType> feeType = feeTypeRepository.findById(feeTypeId);
        if (feeType.isEmpty()) {
            return "That fee type does not exist";
        }
        Cashier c = cashier.get();
        for (FeeType f : c.getFeeTypes()) {
            if (f.getId().equals(feeTypeId)) {
                return "The cashier already has that fee type";
            }
        }
        c.getFeeTypes().add(feeType.get());
        cashierRepository.save(c);
        return "Fee type added to the cashier";
    }

       public String changeCounter(UUID cashierId, int counterNumber) {
        Optional<Cashier> cashier = cashierRepository.findById(cashierId);
        if (cashier.isEmpty()) {
            return "The cashier with that id does not exist";
        }
        Optional<Counter> counter = counterRepository.findByCounterNumber(counterNumber);
        if (counter.isEmpty()) {
            return "The counter with that counterNumber does not exist";
        }
        Cashier c = cashier.get();
        boolean alreadyHis = c.getCounter() != null
                && c.getCounter().getId().equals(counter.get().getId());
        if (!alreadyHis && cashierRepository.existsByCounter(counter.get())) {
            return "That counter is taken by another cashier";
        }
        c.setCounter(counter.get());
        cashierRepository.save(c);
        return "Counter changed successfully";
    }
}
