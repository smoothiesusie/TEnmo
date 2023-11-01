package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int id;
    private int transfer_type_id;
    private int transfer_status_id;
    private User account_from;
    private User account_to;
    private BigDecimal amount;

    public int getId() {
        return id;
    }

    public int getTransfer_type_id() {
        return transfer_type_id;
    }

    public int getTransfer_status_id() {
        return transfer_status_id;
    }

    public User getAccount_from() {
        return account_from;
    }

    public User getAccount_to() {
        return account_to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    Transfer(User account_from, User account_to, BigDecimal amount) {
        this.account_from = account_from;
        this.account_to = account_to;
        this.amount = amount;
    }
}
