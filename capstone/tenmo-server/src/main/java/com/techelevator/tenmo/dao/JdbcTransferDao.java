package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.tenmo.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transfer> getTransfers() {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount" +
                " FROM public.transfer;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Transfer transfer = mapRowToTransfer(results);
                transfers.add(transfer);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfers;
    }

    @Override
    public Transfer getTransferById(int id) {
        Transfer transfer = null;
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount" +
                " FROM transfer WHERE transfer_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                transfer = mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return transfer;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        Transfer newTransfer = null;
        String sql = "INSERT INTO transfer(" +
                " transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
                " VALUES (?, ?, ?, ?, ?) RETURNING transfer_id;";
        try {
            int transferId = jdbcTemplate.queryForObject(sql, int.class, transfer.getTransfer_type_id(), transfer.getTransfer_status_id(),
                     transfer.getAccount_from(), transfer.getAccount_to(), transfer.getAmount());
            newTransfer = getTransferById(transferId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newTransfer;
    }

    @Override
    public Transfer updateTransfer(Transfer transfer) {
        Transfer updatedTransfer = null;
        String sql = "UPDATE public.transfer" +
                " SET transfer_type_id=?, transfer_status_id=?, account_from=?, account_to=?, amount=?" +
                " WHERE transfer_id=?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, transfer.getTransfer_type_id(), transfer.getTransfer_status_id(),
                    transfer.getAccount_from(), transfer.getAccount_to(), transfer.getAmount(), transfer.getId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedTransfer = getTransferById(transfer.getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedTransfer;
    }

    @Override
    public int deleteTransferById(int id) {
        int numberOfRows = 0;
        String sql = "DELETE FROM transfer WHERE id = ?";
        try {
            numberOfRows = jdbcTemplate.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private Transfer mapRowToTransfer(SqlRowSet rs){
        Transfer tr = new Transfer();

        tr.setId(rs.getInt("id"));
        tr.setTransfer_type_id(rs.getInt("transfer_type_id"));
        tr.setTransfer_status_id(rs.getInt("transfer_status_id"));
        tr.setAccount_from(rs.getObject("account_from", User.class));
        tr.setAccount_to(rs.getObject("account_to", User.class));
        tr.setAmount(rs.getBigDecimal("amount"));
        return tr;
    }
}
