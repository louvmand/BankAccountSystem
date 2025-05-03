package org.example.exchange;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ExchangeRateClientTest {

    private HttpURLConnection mockConnection;

    @BeforeEach
    void setUp() {
        mockConnection = mock(HttpURLConnection.class);
    }

    @Test
    void fetchExchangeRate_ShouldReturnConversionRate() throws IOException {
        Currency fromCurrency = Currency.getInstance("USD");
        Currency toCurrency = Currency.getInstance("EUR");
        String mockApiResponse = new JSONObject()
                .put("conversion_rate", 0.10)
                .toString();
        ByteArrayInputStream mockInputStream = new ByteArrayInputStream(mockApiResponse.getBytes());
        when(mockConnection.getInputStream()).thenReturn(mockInputStream);

        ExchangeRateClient exchangeRateClient = new ExchangeRateClient(mockConnection);
        double conversionRate = exchangeRateClient.fetchExchangeRate(fromCurrency, toCurrency);

        assertEquals(0.10, conversionRate);
        verify(mockConnection).setRequestMethod("GET");
    }

    @Test
    void fetchExchangeRate_ShouldThrowIOException_OnApiError() throws IOException {
        Currency fromCurrency = Currency.getInstance("USD");
        Currency toCurrency = Currency.getInstance("EUR");
        when(mockConnection.getInputStream()).thenThrow(new IOException("API error"));

        ExchangeRateClient exchangeRateClient = new ExchangeRateClient(mockConnection);

        IOException exception = assertThrows(IOException.class, () ->
                exchangeRateClient.fetchExchangeRate(fromCurrency, toCurrency)
        );

        assertEquals("API error", exception.getMessage());
    }
}