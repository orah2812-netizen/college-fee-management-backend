package com.auca.collegefeemanagement.controller;


import com.auca.collegefeemanagement.model.Cashier;
import com.auca.collegefeemanagement.service.service.CashierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cashiers")


public class CashierController {

    @Autowired 
    private CashierService cashierService;
    @PostMapping ("/save")
    public ResponseEntity<String> save(@RequestBody Cashier cashier) {

        String message = cashierService.save(cashier);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }   

    @GetMapping ("/all")
    public List<Cashier> getAll() {
        return cashierService.getAll();
    }
        @GetMapping("/by-fee-type")
    public List<Cashier> getByFeeType(@RequestParam String name) {
        return cashierService.getByFeeType(name);
    }

     @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Cashier> cashier = cashierService.getById(id);
        if (cashier.isPresent()) {
            return ResponseEntity.ok(cashier.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The cashier with that id does not exist");
        }
    }

      @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Cashier cashier) {
        String message = cashierService.update(id, cashier);
        if (message.equals("The cashier with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = cashierService.delete(id);
        if (message.equals("The cashier with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }
        @PutMapping("/{cashierId}/fee-types/{feeTypeId}")
    public ResponseEntity<String> addFeeType(@PathVariable UUID cashierId, @PathVariable UUID feeTypeId) {
        String message = cashierService.addFeeType(cashierId, feeTypeId);
        if (message.endsWith("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.equals("The cashier already has that fee type")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
           @PatchMapping("/{cashierId}/counter")
    public ResponseEntity<String> changeCounter(@PathVariable UUID cashierId, @RequestParam int counterNumber) {
        String message = cashierService.changeCounter(cashierId, counterNumber);
        if (message.endsWith("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.equals("That counter is taken by another cashier")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
