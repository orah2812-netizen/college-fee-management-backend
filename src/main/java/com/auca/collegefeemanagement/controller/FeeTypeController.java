package com.auca.collegefeemanagement.controller;
import com.auca.collegefeemanagement.model.FeeType;
import com.auca.collegefeemanagement.service.FeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fee-types")
public class FeeTypeController {

    @Autowired
    private FeeTypeService feeTypeService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody FeeType feeType) {
        String message = feeTypeService.save(feeType);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<FeeType> getAll() {
        return feeTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<FeeType> feeType = feeTypeService.getById(id);
        if (feeType.isPresent()) {
            return ResponseEntity.ok(feeType.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("The fee type with that id does not exist");
        }
    }
     @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody FeeType feeType) {
        String message = feeTypeService.update(id, feeType);
        if (message.equals("The fee type with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = feeTypeService.delete(id);
        if (message.equals("The fee type with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }
}
