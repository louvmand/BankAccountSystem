package org.example.exchange;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeResponseFormatterTest {

    private final ExchangeResponseFormatter formatter = new ExchangeResponseFormatter();

    @Test
    void formatExchangeRateResponse_ShouldReturnFormattedString() {
        String fromCurrencyCode = "USD";
        String toCurrencyCode = "EUR";
        double conversionRate = 0.85;

        String result = formatter.formatExchangeRateResponse(fromCurrencyCode, toCurrencyCode, conversionRate);

        String expected = "{\"USD\": 100.00, \"EUR\": 85.00}";
        assertEquals(expected, result);
    }
}