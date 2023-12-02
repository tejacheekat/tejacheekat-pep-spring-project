package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Integer>{


    Optional<Account> findByUsername(String username);
    Account findByUsernameAndPassword(String username, String password);
    @Query("SELECT a FROM Account a WHERE a.account_id = :account_id")
    Account findAccountByAccountId(Integer account_id);
} 
