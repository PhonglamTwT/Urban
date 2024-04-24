package com.example.Urban.service;

import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;

import com.example.Urban.dto.EmployeeAccountDTO;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeEntity> getAllEmployee();

    public List<AccountEntity> getAllAccount();
  
    public Boolean updateEmployee(int employeeId, EmployeeAccountDTO employeeAccountDTO);

    public Boolean deleteEmployee(int employeeId);

    public EmployeeEntity createEmployeeAndAccount(EmployeeAccountDTO employeeAccountDTO);
}
