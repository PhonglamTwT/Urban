package com.example.Urban.service;


import com.example.Urban.dto.EmployeeAccountDTO;

public interface ManagerService {
    Boolean updateEmployee(int id, EmployeeAccountDTO employeeAccountDTO);
}
