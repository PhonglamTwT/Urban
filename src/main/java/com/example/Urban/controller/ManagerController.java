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
