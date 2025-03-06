package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.model.Employee;
import com.example.EmployeePayroll.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepository; // Dependency Injection

    // Get all users
    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }

    // Get user by ID
    public Optional<Employee> getUserById(Long id) {
        return employeeRepository.findById(id);
    }

    // Create user
    public Employee createUser(Employee user) {
        return employeeRepository.save(user);
    }

    // Update user
    public Optional<Employee> updateUser(Long id, Employee userDetails) {
        return employeeRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());  // Update name
            user.setSalary(userDetails.getSalary());  // Corrected salary update
            return employeeRepository.save(user);
        });
    }

    // Delete user
    public boolean deleteUser(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
