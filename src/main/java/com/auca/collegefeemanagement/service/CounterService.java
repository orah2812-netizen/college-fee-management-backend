package com.auca.collegefeemanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.collegefeemanagement.model.Counter;
import com.auca.collegefeemanagement.repository.CounterRepository;

@Service

public class CounterService {
    @Autowired 
    public CounterRepository counterRepository;

    public String save(Counter counter) {
        counterRepository.save(counter);
        return "Counter saved successfully";
    }
    public List<Counter> getAll() {
        return counterRepository.findAll();
    }
    public Optional<Counter> getById(UUID id) {
        return counterRepository.findById(id);
    }

    public String update(UUID id, Counter counter) {
        Optional<Counter> existing = counterRepository.findById(id);
        if (existing.isEmpty()) { 
            return "The counter with that id does not exist";
        }

        Counter old = existing.get();
        old.setCounterNumber(counter.getCounterNumber());
        old.setName(counter.getName());
        counterRepository.save(old);
        return "Counter updated successfully";
    }
    public String delete(UUID id) {
        if (!counterRepository.existsById(id)) {
            return "The counter with that id does not exist";
        }
        counterRepository.deleteById(id);
        return "Counter deleted successfully";
    }
}
