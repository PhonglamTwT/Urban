package com.example.Urban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Urban.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    
}
