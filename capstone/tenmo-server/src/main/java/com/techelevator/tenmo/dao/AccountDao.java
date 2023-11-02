package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDao {
    List<Account> getAccount();
    public Account getAccountById(int id);
    public Account getAccountByUserId(int id);
    public Account createAccount(Account account);
    public Account updateAccount(Account account);
    public int deleteAccountById(int id);
}
