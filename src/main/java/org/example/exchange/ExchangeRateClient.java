package org.example.exchange;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Currency;

@ApplicationScoped
public class ExchangeRateClient {

    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/c04f28e1f98f6f0c79c59e09/pair";
    private HttpURLConnection connection;

    public ExchangeRateClient() {
    }

    public ExchangeRateClient(HttpURLConnection connection) {
        this.connection = connection;
    }

    public double fetchExchangeRate(Currency fromCurrency, Currency toCurrency) throws IOException {
        String apiUrl = String.format("%s/%s/%s", API_BASE_URL, fromCurrency.getCurrencyCode(), toCurrency.getCurrencyCode());
        URI uri = URI.create(apiUrl);
        URL url = uri.toURL();

        HttpURLConnection conn = (connection != null) ? connection : (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            String jsonResponse = response.toString();
            return new org.json.JSONObject(jsonResponse).getDouble("conversion_rate");
        }
    }
}