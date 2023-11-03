package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    private int id;
    private int user_id;
    private BigDecimal balance;

    public int getId() {
        return id;
    }

    public void setId(int account_id) {
        this.id = account_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account(int account_id, int user_id, BigDecimal balance) {
        this.id = account_id;
        this.user_id = user_id;
        this.balance = balance;
    }

    public Account() {
    }
}
