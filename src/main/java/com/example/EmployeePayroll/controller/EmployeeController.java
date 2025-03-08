package com.example.EmployeePayroll.controller;
import lombok.extern.slf4j.Slf4j;
import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.Employee;
import com.example.EmployeePayroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //UC8
    @GetMapping("/test")
    public String testLogging() {
        log.info("Info Level Log");
        log.debug("Debug Level Log");
        log.error("Error Level Log");
        return "Logging Test";
    }

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
