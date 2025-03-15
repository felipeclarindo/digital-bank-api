package com.example.digital_bank_api.model;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class Account {

    private Long id;

    @NotBlank(message = "The field holderName cant is empty.")
    private String holderName;

    @NotBlank(message = "The field holderCpf cant is empty.")
    private String holderCpf;

    @NotNull(message = "The started balance is need.")
    @PositiveOrZero(message = "The started balance needs to be positive.")
    private BigDecimal startedBalance;

    @Positive(message = "The number needs to be positive.")
    private int number;

    @NotBlank(message = "The field agency cant is empty.")
    private String agency;

    @NotNull(message = "The opening date is needed.")
    @PastOrPresent(message = "The opening date can not be in future.")
    private LocalDate openingDate;

    private AccountState accountState;
    private AccountType accountType;

    public Account(String holderName, String holderCpf, BigDecimal startedBalance, int number, String agency, LocalDate openingDate, AccountState accountState, AccountType accountType) {
        this.holderName = holderName;
        this.holderCpf = holderCpf;
        this.startedBalance = startedBalance;
        this.number = number;
        this.agency = agency;
        this.openingDate = openingDate;
        this.accountState = accountState;
        this.accountType = accountType;
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getHolderName() {
        return this.holderName;
    }

    public String getHolderCpf() {
        return this.holderCpf;
    }

    public BigDecimal getStartedBalance() {
        return this.startedBalance;
    }

    public int getNumber() {
        return this.number;
    }

    public String getAgency() {
        return this.agency;
    }

    public LocalDate getOpeningDate() {
        return this.openingDate;
    }

    public AccountState getAccountState() {
        return this.accountState;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    @Override
    public String toString() {
        return "Account Details\n"  
            + "Holder Name -> " + holderName 
            +  "\nHolder Cpf -> " + holderCpf
            + "\nStarted Balance -> " + startedBalance
            + "\nNumber -> " + number
            + "\nAgency -> " + agency
            + "\nOpening Date ->" + openingDate
            + "\nAccount State ->" + accountState
            + "\nAccount Type -> " + accountType;
    }
}
