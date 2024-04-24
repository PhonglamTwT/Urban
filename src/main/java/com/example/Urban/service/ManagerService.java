package com.example.Urban.service;

import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.repository.AccountRepository;
import com.example.Urban.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Urban.dto.EmployeeAccountDTO;

import java.util.List;

public class ManagerService {

    public List<EmployeeEntity> getAllEmployee();

    public List<AccountEntity> getAllAccount();
  
    Boolean updateEmployee(int id, EmployeeAccountDTO employeeAccountDTO);
}
