package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;

import java.math.BigDecimal;
import java.util.List;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final AccountService accountService = new AccountService();
    private final TransferService transferService = new TransferService();

    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
		Account account = accountService.getAccountByUserId(currentUser.getUser().getId());
        System.out.println(account.getBalance());
	}

	private void viewTransferHistory() {
        // TODO needs sql calls
        Account account = accountService.getAccountByUserId(currentUser.getUser().getId());
        Transfer[] transfers = transferService.getTransferHistory(account.getId());
        transferService.printTransfers(transfers);
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		Transfer[] transfers = transferService.getTransferByStatus(currentUser.getUser().getId());
        transferService.printTransfers(transfers);
	}

	private void sendBucks() {
		// TODO Auto-generated method stub
        //called user service to retrieve list of users from the backend
        //prompted to collect amount of bucks you want to send

        int toUserId = consoleService.promptForInt("Enter Id of receiver.");
        BigDecimal amount = consoleService.promptForBigDecimal("Enter amount being sent");
        int fromUserId = currentUser.getUser().getId();

        //set variable in the Transfer Model in Server
        TransferDTO transferDTO = new TransferDTO(fromUserId, toUserId, amount, TransferDTO.transfer_type_send);
        Transfer transfer = transferService.addTransfer(transferDTO);
        //prompted to select a user (id, name, etc)
        if(transfer != null){
            System.out.println(amount + "TE bucks were sent to User"+ toUserId);
        } else {
            consoleService.printErrorMessage();
        }


        //**constructed a transferDTO object, sent it through the transfer service(line 122) to the backend
        //**decided how you want to send this info(sender, receiver, amount) to the backend

	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
