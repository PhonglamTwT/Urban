package com.example.Urban.controller;

import com.example.Urban.dto.EmployeeAccountDTO;
import com.example.Urban.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @PostMapping("/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestParam int employeeId, @RequestBody EmployeeAccountDTO employeeAccountDTO){
        try{
            if(managerService.updateEmployee(employeeId, employeeAccountDTO)){
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Fail",HttpStatus.OK);
            }

        }catch (Exception e) {
            return new ResponseEntity<>("Fail", HttpStatus.OK);
        }
    }
}
