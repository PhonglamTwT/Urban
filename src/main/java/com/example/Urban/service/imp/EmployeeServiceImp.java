package com.example.Urban.service.imp;

import com.example.Urban.dto.EmployeeAccountDTO;
import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.repository.AccountRepository;
import com.example.Urban.repository.EmployeeRepository;
import com.example.Urban.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<EmployeeEntity> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<AccountEntity> getAllAccount() {
        return accountRepository.findAll();
    }

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
            System.out.println("ManagerServiceImp updateEmployee Error: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteEmployee(int employeeId) {
        try{
            EmployeeEntity employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
            AccountEntity account = employee.getAccount();
            accountRepository.deleteById(account.Getid());
            employeeRepository.deleteById(employeeId);
            return true;
        }catch (Exception e){
            System.out.println("ManagerServiceImp deleteEmployee Error: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Transactional
    public EmployeeEntity createEmployeeAndAccount(EmployeeAccountDTO employeeAccountDTO){

        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(employeeAccountDTO.getName());
        employee.setPosition(employeeAccountDTO.getPosition());
        employee.setHeadquarter(employeeAccountDTO.getHeadquarter());
        employeeRepository.save(employee);

        AccountEntity account = new AccountEntity();
        account.setUsername(employeeAccountDTO.getUsername());
        account.setPassword(employeeAccountDTO.getPassword());
        account.setRole(employeeAccountDTO.getRole());
        account.setEmployee(employee);
        accountRepository.save(account);

        return employee;

    }
}
