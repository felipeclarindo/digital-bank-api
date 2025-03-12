package com.example.digital_bank_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.digital_bank_api.model.Account;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private List<Account> repository = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(200).body(repository);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Object response = repository.stream()
        .filter(a -> a.getId().equals(id))
        .findFirst();
        return ResponseEntity.status(200).body(response);
    }
}
