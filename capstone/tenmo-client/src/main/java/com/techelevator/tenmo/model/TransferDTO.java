package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {
    private int toUser;
    private int fromUser;
    private BigDecimal amount;
    private String transfer_type;

    //set variables for transfer types and status
    public static final String transfer_type_send = "send";
    public static final String transfer_type_request = "request";
    public static final String transfer_type_approved = "approved";
    public static final String transfer_type_rejected = "rejected";
    public static final String transfer_type_pending = "pending";



    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    public int getFromUser() {
        return fromUser;
    }

    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransfer_type() {
        return transfer_type;
    }

    public void setTransfer_type(String transfer_type) {
        this.transfer_type = transfer_type;
    }

    public TransferDTO(int toUser, int fromUser, BigDecimal amount, String transfer_type) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.amount = amount;
        this.transfer_type = transfer_type;
    }

    public TransferDTO() {
    }
}

