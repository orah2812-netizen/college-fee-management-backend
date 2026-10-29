package com.auca.collegefeemanagement.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.auca.collegefeemanagement.model.Receipt;
import com.auca.collegefeemanagement.service.service.ReceiptService;

@RestController 
@RequestMapping("/api/receipts")

public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID feePaymentId, @RequestBody Receipt receipt) {
        String message = receiptService.save(feePaymentId, receipt);
        if (message.equals("The fee payment with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.equals("Receipts are only for POSTED fee payments")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
        @GetMapping("/student/{regNumber}")
    public List<Receipt> getByStudent(@PathVariable String regNumber) {
        return receiptService.getByStudent(regNumber);
    }

        @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = receiptService.delete(id);
        if (message.equals("The receipt with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
}
