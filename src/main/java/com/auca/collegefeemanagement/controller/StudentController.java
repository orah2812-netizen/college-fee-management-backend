package com.auca.collegefeemanagement.controller;
import com.auca.collegefeemanagement.model.Student;
import com.auca.collegefeemanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/students")

public class StudentController {
    @Autowired 
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Student student) {
        String message = studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<Student> getAll() {
        return studentService.getAll();
    }

      @GetMapping("/by-last-name")
    public List<Student> getByLastName(@RequestParam String lastName) {
        return studentService.getByLastName(lastName);
    }
    
        @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Student> student = studentService.getById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The student with that id does not exist");
        }
    }
@PutMapping("/update/{id}")

public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Student student) {
        String message = studentService.update(id, student);
        if (message.equals("The student with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = studentService.delete(id);
        if (message.equals("The student with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }
}
