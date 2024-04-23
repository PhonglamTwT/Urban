package com.example.Urban.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private int id;
    private String password;
    private String username;
    private String role;
}
