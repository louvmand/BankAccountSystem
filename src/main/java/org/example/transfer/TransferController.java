package org.example.transfer;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;

@Path("/transfers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransferController {

    @Inject
    TransferService transferService;

    @POST
    public void transfer(TransferRequest request) {
        transferService.transfer(request.fromAccount, request.toAccount, request.amount);
    }

    @PUT
    @Path("/deposit")
    public void deposit(@QueryParam("accountNumber") String accountNumber, @QueryParam("amount") BigDecimal amount) {
        transferService.deposit(accountNumber, amount);
    }
}
