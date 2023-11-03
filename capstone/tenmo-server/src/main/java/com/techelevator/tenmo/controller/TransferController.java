package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
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

    public TransferController(TransferDao transferDao, UserDao userDao) {
        this.transferDao = transferDao;
    }

    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public List<Transfer> getTransfer(@RequestParam(defaultValue = "0") int account_to) {

        if (account_to > 0){
            return transferDao.getTransferByToAccountId(account_to);
        }else{
            return transferDao.getTransfers();
        }

    }

    @RequestMapping(path = "/transfer/{id}/history", method = RequestMethod.GET)
    public List<Transfer> getTransferHistory(@PathVariable int id) {
        return transferDao.getTransferHistory(id);
    }


    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable int id) {
        Transfer transfer = transferDao.getTransferById(id);
        if (transfer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry we were unable to locate that transfer.");
        } else {
            return transfer;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public Transfer createTransfer(@RequestBody @Valid Transfer transfer) {
        return transferDao.createTransfer(transfer);
    }

    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.PUT)
    public Transfer updateTransfer(@RequestBody @Valid Transfer transfer, @PathVariable int id) {
        transfer.setId(id);
        try {
            Transfer updatedTransfer = transferDao.updateTransfer(transfer);
            return updatedTransfer;
        } catch (DaoException dx) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry we were unable to update the given transfer");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.DELETE)
    public void deleteTransferById(@PathVariable int id) {
        try {
            transferDao.deleteTransferById(id);
        } catch (DaoException dx) {
            throw new ResponseStatusException(HttpStatus.GONE);
        }
    }
}
