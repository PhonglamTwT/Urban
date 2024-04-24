package com.example.Urban.controller;

import com.example.Urban.dto.EmployeeAndAccountDTO;
import com.example.Urban.entity.AccountEntity;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.service.EmployeeAndAccountService;
import com.example.Urban.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private EmployeeAndAccountService employeeAndAccountService;
    @GetMapping("/showEmploy")
    public ResponseEntity<List<EmployeeEntity>> GetAllEmployee() {

        List<EmployeeEntity> employee = managerService.getAllEmployee();


            return ResponseEntity.ok().body(employee);

    }


    @PostMapping("/addEmploy")
    public ResponseEntity<String> AddEmployeeAndAccount(@RequestBody EmployeeAndAccountDTO employeeAndAccountDTO) {

        EmployeeEntity employee = employeeAndAccountService.createEmployeeAndAccount(employeeAndAccountDTO);

        return ResponseEntity.ok().body("Employee and account added successfully with employee ID: " + employee.getId());

    }





}
