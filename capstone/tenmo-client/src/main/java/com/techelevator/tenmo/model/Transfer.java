package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int id;
    private int transfer_type_id;
    private int transfer_status_id;
    private int account_from;
    private int account_to;
    private BigDecimal amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransfer_type_id() {
        return transfer_type_id;
    }

    public void setTransfer_type_id(int transfer_type_id) {
        this.transfer_type_id = transfer_type_id;
    }

    public int getTransfer_status_id() {
        return transfer_status_id;
    }

    public void setTransfer_status_id(int transfer_status_id) {
        this.transfer_status_id = transfer_status_id;
    }

    public int getAccount_from() {
        return account_from;
    }

    public void setAccount_from(int account_from) {
        this.account_from = account_from;
    }

    public int getAccount_to() {
        return account_to;
    }

    public void setAccount_to(int account_to) {
        this.account_to = account_to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Transfer() {
    }

    Transfer(int transfer_status_id, int transfer_type_id, int account_from, int account_to, BigDecimal amount) {
        this.transfer_status_id = transfer_status_id;
        this. transfer_type_id =  transfer_type_id;
        this.account_from = account_from;
        this.account_to = account_to;
        this.amount = amount;
    }

    @Override
    public String toString() {
        String transfer_type = "";
        String transfer_status = "";
        if (transfer_type_id == 1) {
            transfer_type = "Request";
        } else if (transfer_type_id == 2) {
            transfer_type = "Send";
        }
        if (transfer_status_id == 1) {
            transfer_status = "Pending";
        } else if (transfer_status_id == 2) {
            transfer_status = "Approved";
        } else if (transfer_status_id == 3) {
            transfer_status = "Rejected";
        }
        return "Transfer{" + "id=" + id + ", transfer_type='" + transfer_type+ ", transfer_status=" + transfer_status +
                ", account_from=" + account_from  + ", account_to=" + account_to + ", amount=" + amount + "}";
    }
}
