package com.example.digital_bank_api.model;

import java.sql.Date;

public class Account {
    private String holderName;
    private int holderCpf;
    private float balance;
    private int number;
    private int agency;
    private Date openingDate;
    private AccountState accountState;
    private AccountType accountType;

    public String getHolderName() {
        return this.holderName;
    }

    public int getHolderCpf() {
        return this.holderCpf;
    }

    public float getBalance() {
        return this.balance;
    }

    public int getNumber() {
        return this.number;
    }

    public int getAgency() {
        return this.agency;
    }

    public Date getOpeningDate() {
        return this.openingDate;
    }

    public AccountState getAccountState() {
        return this.accountState;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }
}
