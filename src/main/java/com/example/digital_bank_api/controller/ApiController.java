package com.example.digital_bank_api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping
    public ResponseEntity<Object> getApiDescription() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Api is running.");
        response.put("description", "Api for a digital bank developed in java using Spring");
        response.put("author", "felipeclarindo");
        response.put("github-repository", "https://github.com/felipeclarindo/digital-bank-api");
        response.put("github-author", "https://github.com/felipeclarindo");
        return ResponseEntity.status(200).body(response);
    }
}
