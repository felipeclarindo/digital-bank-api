package com.example.digital_bank_api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.digital_bank_api.model.Account;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private List<Account> repository = new ArrayList<>();

    @GetMapping
    public ResponseEntity<Object> getAll() {        
        if (repository.isEmpty()) {
            return ResponseEntity.status(404).body("No accounts added.");
        }
        return ResponseEntity.status(200).body(repository);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Object response = repository.stream()
        .filter(account -> account.getId().equals(id))
        .findFirst(); 

        if (response.equals(Optional.empty())) {
            log.info("Error to get the account with id equals to " + id);
            return ResponseEntity.status(404).body("The account with id " + id + " wasn't found.");
        }
        log.info("The account with id " + id + "was found");
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Account account) {
        
        if (account == null) {
            return ResponseEntity.status(404).body("The RequestBody can not be empty.");
        }

        log.info("Registering account \n" + account.toString());

        long newId = repository.size() + 1;
        account.setId(newId); 
        
        repository.add(account);
        return ResponseEntity.status(201).body(account);
    }
}
