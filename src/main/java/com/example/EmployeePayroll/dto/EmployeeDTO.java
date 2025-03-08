package com.example.EmployeePayroll.dto;

//UC6
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
    private String name;
    private double salary;
}
