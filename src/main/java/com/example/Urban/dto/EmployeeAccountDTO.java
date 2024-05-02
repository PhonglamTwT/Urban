package com.example.Urban.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class EmployeeAccountDTO {
    private String name;
    private String position;
    private String headquarter;
    private String username;
    private String password;
    private String role;
}
