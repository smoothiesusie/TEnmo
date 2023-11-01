package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
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
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount\n" +
                "\tFROM public.transfer;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        }
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

    private Transfer mapRowToTransfer(SqlRowSet rs){
        Transfer tr = new Transfer();

        tr.setId(rs.getInt("id"));
        tr.setTransfer_type_id(rs.getInt("transfer_type_id"));
        tr.setTransfer_status_id(rs.getInt("transfer_status_id"));
        tr.setAccount_from(User);
        tr.setAccount_to(rs.get("account_to"));
        tr.setAmount(rs.getInt("amount"));
        return tr;
    }
}
