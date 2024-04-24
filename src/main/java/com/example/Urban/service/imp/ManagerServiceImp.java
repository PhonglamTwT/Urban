package com.example.Urban.service.imp;

import com.example.Urban.dto.EmployeeAccountDTO;
import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.repository.AccountRepository;
import com.example.Urban.repository.EmployeeRepository;
import com.example.Urban.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImp implements ManagerService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Boolean updateEmployee(int employeeId, EmployeeAccountDTO employeeAccountDTO) {
        EmployeeEntity employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(employeeAccountDTO.getName());
        employee.setPosition(employeeAccountDTO.getPosition());
        employee.setHeadquarter(employeeAccountDTO.getHeadquarter());

        AccountEntity account = employee.getAccount();
        account.setUsername(employeeAccountDTO.getUsername());
        account.setPassword(employeeAccountDTO.getPassword());
        account.setRole(employeeAccountDTO.getRole());
        try{
            employeeRepository.save(employee);
            accountRepository.save(account);
            return true;
        }
        catch (Exception e){
            System.out.println("ManagerService updateEmployee Error: " + e.getLocalizedMessage());
            return false;
        }
    }
}
