package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

public class Main {

    //free api website: https://jobicy.com/jobs-rss-feed
    private static final String MAIN_URI = "https://jobicy.com/api/v2/remote-jobs";

    public static void main(String[] args) {
        try {
            HttpURLConnection connection = getHttpURLConnectionWith(MAIN_URI);
            connection.setRequestMethod("GET");

            String response = getResponse(connection);
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getResponse(final HttpURLConnection connection) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpURLConnection getHttpURLConnectionWith(final String uri) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) URI.create(uri)
                .toURL()
                .openConnection();
        connection.setReadTimeout(8000);
        connection.setConnectTimeout(10000);
        connection.setRequestProperty("Accept", "application/json");
        return connection;
    }
}