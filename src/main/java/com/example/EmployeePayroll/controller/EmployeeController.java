package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.model.Employee;
import com.example.EmployeePayroll.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {
    @Autowired
    EmployeeRepo employeeRepository;

    // Get all employees
    // Example: curl localhost:8080/employeepayrollservice/ -w "\n"
    @GetMapping("/")
    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    // Example: curl localhost:8080/employeepayrollservice/get/1 -w "\n"
    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getUserById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new employee
    // Example: curl -X POST -H "Content-Type: application/json" -d '{"name": "Lisa","salary": 2000}' "http://localhost:8080/employeepayrollservice/create" -w "\n"
    @PostMapping("/create")
    public Employee createUser(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update employee details by ID
    // Example: curl -X PUT -H "Content-Type: application/json" -d '{"name": "Lisa","salary": 2500}' "http://localhost:8080/employeepayrollservice/update/1" -w "\n"
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateUser(@PathVariable Long id, @RequestBody Employee userDetails) {
        Optional<Employee> optionalUser = employeeRepository.findById(id);

        if (optionalUser.isPresent()) {
            Employee user = optionalUser.get();
            user.setName(userDetails.getName());  // Update name
            user.setSalary(userDetails.getSalary());  // Update salary
            return ResponseEntity.ok(employeeRepository.save(user));
        }
        return ResponseEntity.notFound().build();
    }

    // Delete employee by ID
    // Example: curl -X DELETE localhost:8080/employeepayrollservice/delete/1 -w "\n"
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

