package com.auca.collegefeemanagement.service;
import com.auca.collegefeemanagement.model.Student;
import com.auca.collegefeemanagement.repository.CashierRepository;
import com.auca.collegefeemanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CashierRepository cashierRepository;

    public String save(Student student) {
        studentRepository.save(student);
        return "Student saved successfully";
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> getById(UUID id) {
        return studentRepository.findById(id);
    }

    public List<Student> getByLastName(String lastName) {
        return studentRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }


    public String update(UUID id, Student student) {
        Optional<Student> existing = studentRepository.findById(id);
        if (existing.isEmpty()) {
            return "The student with that id does not exist";
        }
        Student old = existing.get();
        old.setFirstName(student.getFirstName());
        old.setLastName(student.getLastName());
        old.setRegNumber(student.getRegNumber());
        old.setProgram(student.getProgram());
        old.setPhone(student.getPhone());
        studentRepository.save(old);
        return "Student updated successfully";
    }

    public String delete(UUID id) {
        if (!studentRepository.existsById(id)) {
            return "The student with that id does not exist";
        }
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }
        public boolean cashierExists(UUID cashierId) {
        return cashierRepository.existsById(cashierId);
    }

    public List<Student> getOfCashier(UUID cashierId) {
        return studentRepository.findStudentsOfCashier(cashierId);
    }
}