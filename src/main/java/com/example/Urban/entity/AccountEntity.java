package com.example.Urban.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

}
