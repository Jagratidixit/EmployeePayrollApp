package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.Employee;
import com.example.EmployeePayroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public Employee createUser(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createUser(employeeDTO);
    }

    @GetMapping("/")
    public List<Employee> getAllUsers() {
        return employeeService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getUserById(@PathVariable Long id) {
        return employeeService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
