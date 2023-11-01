package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transfer> getTransfers() {
        return null;
    }

    @Override
    public Transfer getTransferById(int id) {
        return null;
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        return null;
    }

    @Override
    public Transfer updateTransfer(Transfer transfer) {
        return null;
    }

    @Override
    public int deleteTransferById(int id) {
        return 0;
    }
}
