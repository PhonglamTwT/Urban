package com.example.Urban.controller;


import com.example.Urban.dto.EmployeeAccountDTO;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.service.EmployeeService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private EmployeeService EmployeeService;

    @GetMapping("/showEmploy")
    public ResponseEntity<List<EmployeeEntity>> GetAllEmployee() {

        List<EmployeeEntity> employee = EmployeeService.getAllEmployee();


            return ResponseEntity.ok().body(employee);

    }

    @PostMapping("/addEmploy")
    public ResponseEntity<String> addEmployeeAndAccount(@RequestBody EmployeeAccountDTO employeeAndAccountDTO) {

        EmployeeEntity employee = EmployeeService.createEmployeeAndAccount(employeeAndAccountDTO);

        return ResponseEntity.ok().body("Employee and account added successfully with employee ID: " + employee.Getid());
    }

//    @PostMapping("/updateEmployee")
//    public ResponseEntity<?> updateEmployee(@RequestParam int employeeId, @RequestBody EmployeeAccountDTO employeeAccountDTO){
//        try{
//            if(EmployeeService.updateEmployee(employeeId, employeeAccountDTO)){
//                return new ResponseEntity<>("Success", HttpStatus.OK);
//            }
//            else{
//                return new ResponseEntity<>("Fail",HttpStatus.OK);
//            }
//
//        }catch (Exception e) {
//            return new ResponseEntity<>("Fail", HttpStatus.OK);
//        }
//    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestParam int employeeId ,@RequestParam MultipartFile file, @RequestParam String name,
                                            @RequestParam String email, @RequestParam String phone, @RequestParam String gender,
                                            @RequestParam String address, @RequestParam String position, @RequestParam String headquarter,
                                            @RequestParam String username, @RequestParam String password, @RequestParam String role){
        try{
            if(EmployeeService.updateEmployee(employeeId, file, name, email, phone, gender, address, position, headquarter, username, password, role)){
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Fail",HttpStatus.OK);
            }

        }catch (Exception e) {
            return new ResponseEntity<>("Fail", HttpStatus.OK);
        }
    }
    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<?> deleteEmployee(@RequestParam int employeeId){
        try{
            if(EmployeeService.deleteEmployee(employeeId)){
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Fail",HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Fail",HttpStatus.OK);
        }
    }
}
