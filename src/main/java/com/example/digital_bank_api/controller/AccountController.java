package com.example.digital_bank_api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.digital_bank_api.dto.CashoutRequest;
import com.example.digital_bank_api.dto.DepositRequest;
import com.example.digital_bank_api.model.Account;
import com.example.digital_bank_api.validation.FieldsValidator;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private FieldsValidator validator = new FieldsValidator();
    private Logger log = LoggerFactory.getLogger(getClass());
    private List<Account> repository = new ArrayList<>();

    @GetMapping
    public ResponseEntity<Object> getAll() {
        if (repository.isEmpty()) {
            return ResponseEntity.status(404).body("No accounts added.");
        }
        return ResponseEntity.status(200).body(repository);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Object> getByCpf(@PathVariable String cpf) {
        Object response = repository.stream()
                .filter(account -> account.getHolderCpf().equals(cpf))
                .findFirst();
        if (response.equals(Optional.empty())) {
            log.info("Error to get the account with cpf equals to {}", cpf);
            return ResponseEntity.status(404).body("The account with cpf " + cpf + " wasn't found.");
        }
        log.info("The account with cpf {} was found", cpf);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Optional<Account> response = repository.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();
        if (response.equals(Optional.empty())) {
            log.info("Error to get the account with id equals to {}", id);
            return ResponseEntity.status(404).body("The account with id " + id + " wasn't found.");
        }

        log.info("The account with id {} was found", id);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Account account) {
        validator.validateFields(account.getHolderName(),
                account.getHolderCpf(),
                account.getBalance(),
                account.getOpeningDate(),
                account.getAccountType());

        log.info("Registering account \n{}", account);

        Optional<Account> accountWithIdExisting = repository.stream()
                .filter(acc -> acc.getId().equals(account.getId()))
                .findFirst();

        Optional<Account> accountWithCpfExisting = repository.stream()
                .filter(acc -> acc.getHolderCpf().equals(account.getHolderCpf()))
                .findFirst();

        if (accountWithIdExisting.isPresent()) {
            log.info("Error to create the account with id equals to {}", account.getId());
            return ResponseEntity.status(400).body("The account with id " + account.getId() + " already exists.");
        }

        if (accountWithCpfExisting.isPresent()) {
            log.info("Error to create the account with id equals to {}", account.getId());
            return ResponseEntity.status(400)
                    .body("The account with cpf " + account.getHolderCpf() + " already exists.");
        }

        repository.add(account);
        return ResponseEntity.status(201).body("Account with id " + account.getId() + " created with suscessfully");
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        Optional<Account> accountSelected = repository.stream().filter(account -> account.getId().equals(id))
                .findFirst();

        if (accountSelected.equals(Optional.empty())) {
            log.info("Error to delete the account with id equals to {}", id);
            return ResponseEntity.status(404).body("The account with id " + id + " wasn't found.");
        }

        if (!accountSelected.isPresent()) {
            return ResponseEntity.status(404).body("The account with id " + id + " wasn't found.");
        }

        repository.remove(accountSelected.get());
        log.info("The account with id {} was deleted", id);
        return ResponseEntity.status(204).body("The account with id " + id + " was deleted.");
    }

    @PostMapping("/deposit/{id}")
    public ResponseEntity<Object> deposit(@PathVariable Long id, @RequestBody DepositRequest value) {
        BigDecimal valueToDeposit = value.getValue();

        if (repository.isEmpty()) {
            return ResponseEntity.status(404).body("No accounts added.");
        }

        Optional<Account> accountToDeposit = repository.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();

        if (!accountToDeposit.isPresent()) {
            return ResponseEntity.status(404).body("The account with id " + id + " not found.");
        }

        if (valueToDeposit.compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.status(400).body("Value to deposit must be positive.");
        }

        accountToDeposit.get().setBalance(accountToDeposit.get().getBalance().add(valueToDeposit));
        return ResponseEntity.status(200).body("Deposited " + valueToDeposit + " with suscessfully.\n" +
                " New balance is " + accountToDeposit.get().getBalance());
    }

    @PostMapping("/cashout/{id}")
    public ResponseEntity<Object> cashout(@PathVariable Long id, @RequestBody CashoutRequest value) {
        BigDecimal valueToCashout = value.getValue();

        if (valueToCashout.compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.status(400).body("Value to cashout must be positive.");
        }

        Optional<Account> accountToCashout = repository.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();

        if (!accountToCashout.isPresent()) {
            return ResponseEntity.status(404).body("The account with id " + id + " not found.");
        }

        if (accountToCashout.get().getBalance().compareTo(valueToCashout) < 0) {
            return ResponseEntity.status(400).body("Dont have balance to cashout.");
        }

        accountToCashout.get().setBalance(accountToCashout.get().getBalance().subtract(valueToCashout));
        return ResponseEntity.status(200).body("Cashout " + valueToCashout + " with suscessfully.\n" +
                " New balance is " + accountToCashout.get().getBalance());
    }

}
