package com.example.Urban.service;

import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;

import com.example.Urban.dto.EmployeeAccountDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface EmployeeService {

    public List<EmployeeEntity> getAllEmployee();

    public List<AccountEntity> getAllAccount();
  
//    public Boolean updateEmployee(int employeeId, EmployeeAccountDTO employeeAccountDTO);
    public Boolean updateEmployee(int employeeId, MultipartFile file, String name, String email, String phone, String gender, String address,
                                  String position, String headquarter, String username, String password, String role);

    public Boolean deleteEmployee(int employeeId);

    public EmployeeEntity createEmployeeAndAccount(EmployeeAccountDTO employeeAccountDTO);
}
