package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.exception.DaoException;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransferController {

    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    @RequestMapping(path = "/api/transfer", method = RequestMethod.GET)
    public List<Transfer> getTransfer() {
        return transferDao.getTransfers();
    }

    @RequestMapping(path = "/api/transfer/{id}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable int id) {
        Transfer transfer = transferDao.getTransferById(id);
        if (transfer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry we were unable to locate that transfer.");
        } else {
            return transfer;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/api/transfer", method = RequestMethod.POST)
    public Transfer createTransfer(@RequestBody @Valid Transfer transfer) {
        return transferDao.createTransfer(transfer);
    }

    @RequestMapping(path = "/api/transfer/{id}", method = RequestMethod.PUT)
    public Transfer updateTransfer(@RequestBody @Valid Transfer transfer, @PathVariable int id) {
        transfer.setId(id);
        try {
            Transfer updatedTransfer = transferDao.updateTransfer(transfer);
            return transfer;
        } catch (DaoException dx) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry we were unable to update the given transfer");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/api/transfer/{id}", method = RequestMethod.DELETE)
    public void deleteTransferById(@PathVariable int id) {
        try {
            transferDao.deleteTransferById(id);
        } catch (DaoException dx) {
            throw new ResponseStatusException(HttpStatus.GONE);
        }
    }
}
