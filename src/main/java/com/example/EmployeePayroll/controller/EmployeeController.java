package com.example.EmployeePayroll.controller;
import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.Employee;
import com.example.EmployeePayroll.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
@Slf4j //logging
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public Employee createUser(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Creating Employee: {}", employeeDTO);
        return employeeService.createUser(employeeDTO);
    }

    @GetMapping("/")
    public List<Employee> getAllUsers() {
        log.info("Fetching all employees");
        return employeeService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getUserById(@PathVariable Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Employee with ID {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }
}
