package com.example.digital_bank_api.validation;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.example.digital_bank_api.model.AccountType;

public class FieldsValidator {

    public ResponseEntity<Object> validateFields(String holderName, String holderCpf, BigDecimal balance,
            LocalDate openingDate, AccountType accountType) {
        if (!validateHolderName(holderName)) {
            ResponseEntity.status(400).body("Nome do titular inválido.");
        }
        if (!validateHolderCpf(holderCpf)) {
            ResponseEntity.status(400).body("CPF do titular inválido. Deve ter 11 dígitos.");
        }
        if (!validateBalance(balance)) {
            ResponseEntity.status(400).body("Saldo inicial não pode ser negativo.");
        }
        if (!validateOpeningDate(openingDate)) {
            ResponseEntity.status(400).body("Data de abertura inválida. Não pode ser no futuro.");
        }
        if (!validateAccountType(accountType)) {
            ResponseEntity.status(400).body("Tipo de conta inválido.");
        }

        return ResponseEntity.ok("Campos válidos.");
    }

    private boolean validateHolderName(String holderName) {
        return holderName != null && !holderName.isEmpty() && !holderName.isBlank();
    }

    private boolean validateHolderCpf(String cpf) {
        return cpf != null && !cpf.isEmpty() && !cpf.isBlank();
    }

    private boolean validateBalance(BigDecimal balance) {
        return balance.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean validateOpeningDate(LocalDate openingDate) {

        return openingDate != null && !openingDate.isAfter(LocalDate.now());
    }

    private boolean validateAccountType(AccountType accountType) {
        return accountType != null
                && (accountType.equals(AccountType.CURRENT) || accountType.equals(AccountType.SAVINGS)
                        || accountType.equals(AccountType.SALARY));
    }
}
