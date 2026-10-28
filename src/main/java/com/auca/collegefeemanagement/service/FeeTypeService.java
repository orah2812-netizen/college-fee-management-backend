package com.auca.collegefeemanagement.service;

import com.auca.collegefeemanagement.model.FeeType;
import com.auca.collegefeemanagement.repository.FeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeeTypeService {

    @Autowired
    private FeeTypeRepository feeTypeRepository;

    public String save(FeeType feeType) {
        feeTypeRepository.save(feeType);
        return "Fee type saved successfully";
    }

    public List<FeeType> getAll() {
        return feeTypeRepository.findAll();
    }

    public Optional<FeeType> getById(UUID id) {
        return feeTypeRepository.findById(id);
    }
        public List<FeeType> getUnused() {
        return feeTypeRepository.findUnused();
    }

    public String update(UUID id, FeeType feeType) {
        Optional<FeeType> existing = feeTypeRepository.findById(id);
        if (existing.isEmpty()) {
            return "The fee type with that id does not exist";
        }
        FeeType old = existing.get();
        old.setName(feeType.getName());
        feeTypeRepository.save(old);
        return "Fee type updated successfully";
    }

    public String delete(UUID id) {
        if (!feeTypeRepository.existsById(id)) {
            return "The fee type with that id does not exist";
        }
        feeTypeRepository.deleteById(id);
        return "Fee type deleted successfully";
    }
}