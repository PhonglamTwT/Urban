package com.example.Urban.service;

import com.example.Urban.dto.EmployeeAndAccountDTO;
import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.repository.AccountRepository;
import com.example.Urban.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;



public interface EmployeeAndAccountService {

    public EmployeeEntity createEmployeeAndAccount(EmployeeAndAccountDTO DTO);



}
