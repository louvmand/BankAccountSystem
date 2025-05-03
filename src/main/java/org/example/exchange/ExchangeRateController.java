package org.example.exchange;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.Currency;

@Path("/transfers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExchangeRateController {

    @Inject
    ExchangeRateClient exchangeRateClient;
    @Inject
    ExchangeResponseFormatter exchangeResponseFormatter;
    @Inject
    ErrorHandler errorHandler;

    @GET
    @Path("/exchange-rate")
    public Response getExchangeRate(@QueryParam("fromCurrency") String fromCurrencyCode, @QueryParam("toCurrency") String toCurrencyCode) {
        try {
            Currency fromCurrency = Currency.getInstance(fromCurrencyCode);
            Currency toCurrency = Currency.getInstance(toCurrencyCode);
            double conversionRate = exchangeRateClient.fetchExchangeRate(fromCurrency, toCurrency);
            String result = exchangeResponseFormatter.formatExchangeRateResponse(fromCurrencyCode, toCurrencyCode, conversionRate);
            return Response.ok(result, MediaType.APPLICATION_JSON).build();
        } catch (IllegalArgumentException e) {
            return errorHandler.handleBadRequest("Invalid currency code");
        } catch (IOException e) {
            return errorHandler.handleInternalError("Error fetching exchange rate");
        }
    }
}
