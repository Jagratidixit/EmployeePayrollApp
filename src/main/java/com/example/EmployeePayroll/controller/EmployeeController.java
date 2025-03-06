package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.Employee;
import com.example.EmployeePayroll.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepository;

    @PostMapping("/create")
//    curl -X POST -H "Content-Type: application/json" -d '{"name": "John","salary": 5000}' "http://localhost:8080/employeepayrollservice/create" -w "\n"
    public Employee createUser(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }

    @GetMapping("/")
    //curl localhost:8080/employeepayrollservice/ -w "\n"
    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }

    @GetMapping("/get/{id}")
    //curl localhost:8080/employeepayrollservice/get/3 -w "\n"
    public ResponseEntity<Employee> getUserById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
