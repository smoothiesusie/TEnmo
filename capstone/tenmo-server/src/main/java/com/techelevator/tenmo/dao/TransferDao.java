package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    List<Transfer> getTransfers();
    Transfer getTransferById(int id);
    Transfer createTransfer(Transfer transfer);
    Transfer updateTransfer(Transfer transfer);
    int deleteTransferById(int id);
}
