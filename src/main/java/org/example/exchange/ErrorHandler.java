package org.example.exchange;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ErrorHandler {

    public Response handleBadRequest(String message) {
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }

    public Response handleInternalError(String message) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
    }
}
