package com.example.Urban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Urban.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer > {
    
}
