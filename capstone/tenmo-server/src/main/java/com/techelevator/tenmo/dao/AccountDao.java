package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

public interface AccountDao {
    public Account getAccountById(int id);
    public Account getAccountByUserId(int id);
}
