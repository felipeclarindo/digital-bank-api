package com.example.digital_bank_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/")
    public ResponseEntity<String> index() {
        String response = "Digital Bank Api - "
        + "Integrante: Felipe Gabriel Lopes Pinheiro Clarindo\n";
        return ResponseEntity.status(200).body(response);
    }
}
