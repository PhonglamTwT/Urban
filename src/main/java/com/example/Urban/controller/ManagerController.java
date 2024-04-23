package com.example.Urban.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @PostMapping("/insertEmployee")
    public ResponseEntity<?> insertEmployee(@RequestParam String name, @RequestParam String headquarter, @RequestParam String position, @RequestParam String password, @RequestParam String role, @RequestParam String username){
        System.out.println("Check: "+name+" "+headquarter+" "+position+" "+password+" "+role+" "+username);
        String message = "";
        try{
            return new ResponseEntity<>("Upload thanh cong", HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Upload that bai", HttpStatus.OK);
        }
    }
}
