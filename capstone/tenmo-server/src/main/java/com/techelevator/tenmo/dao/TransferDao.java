package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
    List<Transfer> getTransfers();

    List<Transfer> getTransferByToAccountId(int id);
    Transfer getTransferById(int id);
    Transfer createTransfer(Transfer transfer);
    Transfer updateTransfer(Transfer transfer);
    int deleteTransferById(int id);
    List<Transfer> getTransferHistory(int id);
    List<Transfer> getTransferByTransferStatus(int transfer_status_id);

}
