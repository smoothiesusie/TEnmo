package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {
    private AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public List<Account> getAccountByUserId(@RequestParam(defaultValue = "0") int user_id) {

        if (user_id > 0){
            List<Account> accounts = new ArrayList<>();
            accounts.add(accountDao.getAccountByUserId(user_id));
            return accounts;
        }else{
            return accountDao.getAccount();
        }
    }

    @RequestMapping(path = "/account/{id}")
    public Account getAccountById(@PathVariable int id) {
        Account account = accountDao.getAccountById(id);
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry we were unable to locate that account.");
        } else {
            return account;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/account", method = RequestMethod.POST)
    public Account createAccount(@RequestBody @Valid Account account) {
        return accountDao.createAccount(account);
    }

    @RequestMapping(path = "/account/{id}", method = RequestMethod.PUT)
    public Account updateAccount(@RequestBody @Valid Account account, @PathVariable int id) {
        account.setId(id);
        try {
            Account updatedAccount = accountDao.updateAccount(account);
            return updatedAccount;
        } catch (DaoException dx) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry we were unable to update that account.");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/account/{id}", method = RequestMethod.DELETE)
    public void deleteAccountById(@PathVariable int id) {
        try {
            accountDao.deleteAccountById(id);
        } catch (DaoException dx) {
            throw new ResponseStatusException(HttpStatus.GONE);
        }
    }
}
