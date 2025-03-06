package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.Employee;
import com.example.EmployeePayroll.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepository;

    public Employee createUser(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getUserById(Long id) {
        return employeeRepository.findById(id);
    }
}
