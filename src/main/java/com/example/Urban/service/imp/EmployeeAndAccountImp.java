package com.example.Urban.service.imp;

import com.example.Urban.dto.EmployeeAndAccountDTO;
import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.repository.AccountRepository;
import com.example.Urban.repository.EmployeeRepository;
import com.example.Urban.service.EmployeeAndAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAndAccountImp implements EmployeeAndAccountService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public EmployeeEntity createEmployeeAndAccount(EmployeeAndAccountDTO DTO){

        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(DTO.getName());
        employee.setPosition(DTO.getPosition());
        employee.setHeadquarter(DTO.getHeadquarter());
        employeeRepository.save(employee);

        AccountEntity account = new AccountEntity();
        account.setUsername(DTO.getUsername());
        account.setPassword(DTO.getPassword());
        account.setRole(DTO.getRole());
        account.setEmployee(employee);
        accountRepository.save(account);

        return employee;

    }
}
