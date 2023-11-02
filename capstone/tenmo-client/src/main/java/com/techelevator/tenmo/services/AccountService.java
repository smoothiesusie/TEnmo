package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class AccountService {

    public static final String API_BASE_URL = "http://localhost:8080/account/";
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public Account getAccount(int id){
        Account account = null;
        try{
            ResponseEntity<Account> response = restTemplate.exchange(API_BASE_URL + id, HttpMethod.GET, makeAuthEntity(), Account.class);
            account = response.getBody();
        }catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }return account;

    }

    public Account getAccountByUserId(int id) {
        Account account = null;
        try {
            ResponseEntity<Account> response = restTemplate.exchange(API_BASE_URL + "?userId=" + id, HttpMethod.GET,
                    makeAuthEntity(), Account.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return account;
    }

    public Account addAccount(Account account){
        HttpEntity<Account> entity = makeAccountEntity(account);
        Account returnedAccount = null;
        try{
            returnedAccount = restTemplate.postForObject(API_BASE_URL, entity, Account.class);
        }catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return returnedAccount;
    }

    public boolean updatedAccount(Account account){
        HttpEntity<Account>entity = makeAccountEntity(account);
        boolean success = false;
        try{
            restTemplate.put(API_BASE_URL + account.getAccount_id(), entity);
            success = true;
        }catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }

    public boolean deleteAccount(int id){
        boolean success = false;
        try{
            restTemplate.delete(API_BASE_URL + id, HttpMethod.DELETE, makeAuthEntity(), Void.class);
            success = true;
        }catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }




    private HttpEntity<Account> makeAccountEntity(Account account){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(account, headers);
    }


    private HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
