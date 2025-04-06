package com.example.digital_bank_api.dto;

import java.math.BigDecimal;

public class DepositRequest {
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
