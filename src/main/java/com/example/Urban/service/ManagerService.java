package com.example.Urban.service;

import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.repository.AccountRepository;
import com.example.Urban.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public List<AccountEntity> getAllAccount(){
        return accountRepository.findAll();
    }
}
