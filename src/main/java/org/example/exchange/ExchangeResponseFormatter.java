package org.example.exchange;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Locale;

@ApplicationScoped
public class ExchangeResponseFormatter {

    public String formatExchangeRateResponse(String fromCurrencyCode, String toCurrencyCode, double conversionRate) {
        return String.format(Locale.US, "{\"%s\": 100.00, \"%s\": %.2f}", fromCurrencyCode, toCurrencyCode, 100 * conversionRate);
    }
}
