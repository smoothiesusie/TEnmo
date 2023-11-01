package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal currentBalance;

    public BigDecimal getCurrentBalance() {
        return this.currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }
}
