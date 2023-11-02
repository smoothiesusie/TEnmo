package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Account;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcAccountDao implements AccountDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> getAccount(){
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id, user_id, balance FROM accounts";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()){
                Account account = mapRowToAccount(results);
                accounts.add(account);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return accounts;
    }
    @Override
    public Account getAccountById(int id) {
        Account account = null;
        String sql = "SELECT account_id, user_id, balance" +
                " FROM account WHERE account_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                account = mapRowToAccount(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return account;
    }

    @Override
    public Account getAccountByUserId(int id) {
        Account account = null;
        String sql = "SELECT account_id, user_id, balance" +
                " FROM account WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                account = mapRowToAccount(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return account;
    }

    @Override
    public Account createAccount(Account account){
        Account newAccount = null;
        String sql = "INSERT INTO accounts(user_id, balance) VALUES (?, ?) RETURNING id";
        try{
            int accountId = jdbcTemplate.queryForObject(sql, int.class, account.getUserId(), account.getBalance() );
            newAccount = getAccountById(accountId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return newAccount;
    }

    @Override
    public Account updateAccount(Account account){
        Account updatedAccount = null;
        String sql = "UPDATE accounts SET user_id = ?, balance = ? WHERE account_id = ?";
        try{
            int rowsAffected = jdbcTemplate.update(sql, account.getUserId(), account.getBalance());
            if(rowsAffected == 0){
                throw new DaoException("Zero rows affected");
            }
            updatedAccount = getAccountById(account.getId());
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return updatedAccount;
    }

    @Override
    public int deleteAccountById(int id){
        int numberOfRows = 0;
        String sql = "DELETE FROM accounts WHERE id = ?";
        try{
            numberOfRows = jdbcTemplate.update(sql, id);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private Account mapRowToAccount(SqlRowSet rs) {
        Account ac = new Account();

        ac.setId(rs.getInt("account_id"));
        ac.setUserId(rs.getInt("user_id"));
        ac.setBalance(rs.getInt("balance"));

        return ac;
    }
}
