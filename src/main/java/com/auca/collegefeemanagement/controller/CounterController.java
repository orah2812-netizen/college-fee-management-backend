package com.auca.collegefeemanagement.controller;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import com.auca.collegefeemanagement.model.Counter;
import com.auca.collegefeemanagement.service.CounterService;

@RestController
@RequestMapping("/api/counters")

public class CounterController {
    @Autowired 
    private CounterService counterService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Counter counter) {
        String message = counterService.save(counter);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<Counter> getAll() {
        return counterService.getAll();
    }
        @GetMapping("/free")
    public List<Counter> getFree() {
        return counterService.getFree();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Counter> counter = counterService.getById(id);
        if (counter.isPresent()) {
            return ResponseEntity.ok(counter.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The counter with that id does not exist");
        }
    }
  @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Counter counter) {
        String message = counterService.update(id, counter);
        if (message.equals("The counter with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    
    }
     @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = counterService.delete(id);
        if (message.equals("The counter with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }
}
