package com.example.digital_bank_api.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;


public class Account {

    private Long id;

    private String holderName;

    private String holderCpf;

    private BigDecimal startedBalance;

    private int number;

    private String agency;

    private LocalDate openingDate;

    private AccountState accountState;

    private AccountType accountType;

    public Account(
        String holderName,
        String holderCpf,
        BigDecimal startedBalance,
        int number,
        String agency,
        LocalDate openingDate,
        AccountState accountState,
        AccountType accountType
    ) {
        this.id = Math.abs(new Random().nextLong());
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

    public Account setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getHolderName() {
        return this.holderName;
    }

    public Account setHolderName(String holderName) {
        this.holderName = holderName;
        return this;
    }

    public String getHolderCpf() {
        return this.holderCpf;
    }

    public Account setHolderCpf(String holderCpf) {
        this.holderCpf = holderCpf;
        return this;
    }

    public BigDecimal getStartedBalance() {
        return this.startedBalance;
    }

    public Account setStartedBalance(BigDecimal startedBalance) {
        this.startedBalance = startedBalance;
        return this;
    }

    public int getNumber() {
        return this.number;
    }

    public Account setNumber(int number) {
        this.number = number;
        return this;
    }

    public String getAgency() {
        return this.agency;
    }

    public Account setAgency(String agency) {
        this.agency = agency;
        return this;
    }

    public LocalDate getOpeningDate() {
        return this.openingDate;
    }

    public Account setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
        return this;
    }

    public AccountState getAccountState() {
        return this.accountState;
    }
    
    public Account setAccountState(AccountState accountState) {
        this.accountState = accountState;
        return this;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public Account setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
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
