package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    private int id;
    private int userId;
    private BigDecimal balance;

    public int getId() {
        return id;
    }

    public void setId(int account_id) {
        this.id = account_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account(int account_id, int userId, BigDecimal balance) {
        this.id = account_id;
        this.userId = userId;
        this.balance = balance;
    }

    public Account() {
    }
}
