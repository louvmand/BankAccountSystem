package org.example.account;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.account.domain.Account;

import java.math.BigDecimal;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountController {

    @Inject
    AccountService accountService;

    @POST
    public void createAccount(Account account) {
        accountService.createAccount(account);
    }

    @GET
    @Path("/{accountNumber}")
    public Account getAccount(@PathParam("accountNumber") String accountNumber) {
        return accountService.getAccount(accountNumber);
    }

    @GET
    @Path("/{accountNumber}/balance")
    public BigDecimal getBalance(@PathParam("accountNumber") String accountNumber) {
        return accountService.getBalance(accountNumber);
    }
}