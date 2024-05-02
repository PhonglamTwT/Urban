package com.example.Urban.controller;


import com.example.Urban.dto.EmployeeAccountDTO;
import com.example.Urban.dto.ReqRes;
import com.example.Urban.entity.EmployeeEntity;
import com.example.Urban.service.EmployeeService;
import com.example.Urban.service.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private EmployeeService EmployeeService;
    @Autowired
    private UsersManagementService usersManagementService;

//--------------------------------------------------------------------------------------------------

    @PostMapping("/admin/add")
    public ResponseEntity<ReqRes> addEmployeeAndAccountJwt(@RequestBody ReqRes reg){
        return ResponseEntity.ok(usersManagementService.addEmployeeAndAccountJwt(reg));
    }
    @PostMapping("/public/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.loginJwt(req));
    }
    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllEmployeeJwt(){
        return ResponseEntity.ok(usersManagementService.getAllEmployeeJwt());
    }
    @DeleteMapping("/admin/delete/{employeeId}")
    public ResponseEntity<ReqRes> deleteEmployeeJwt(@PathVariable int employeeId){
        return ResponseEntity.ok(usersManagementService.deleteEmployeeJwt(employeeId));
    }
    @PutMapping("/adminnhanvien/update/{employeeId}")
    public ResponseEntity<ReqRes> updateEmployeeJwt(@PathVariable int employeeId, @RequestBody EmployeeAccountDTO employeeAccountDTO){
        return ResponseEntity.ok(usersManagementService.updateEmployeeJwt(employeeId, employeeAccountDTO));
    }

//--------------------------------------------------------------------------------------------------

    @GetMapping("/auth/showEmploy")
    public ResponseEntity<List<EmployeeEntity>> GetAllEmployee() {

        List<EmployeeEntity> employee = EmployeeService.getAllEmployee();

            return ResponseEntity.ok().body(employee);

    }

    @PostMapping("/addEmploy")
    public ResponseEntity<String> addEmployeeAndAccount(@RequestBody EmployeeAccountDTO employeeAndAccountDTO) {

        EmployeeEntity employee = EmployeeService.createEmployeeAndAccount(employeeAndAccountDTO);

        return ResponseEntity.ok().body("Employee and account added successfully with employee ID: " + employee.Getid());
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestParam int employeeId, @RequestBody EmployeeAccountDTO employeeAccountDTO){
        try{
            if(EmployeeService.updateEmployee(employeeId, employeeAccountDTO)){
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
