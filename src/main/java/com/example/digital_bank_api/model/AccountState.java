package com.example.digital_bank_api.model;

public enum AccountState {
    ACTIVE, 
    INACTIVE;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}