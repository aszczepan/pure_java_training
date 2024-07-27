package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

public class Main {

    //free api website: https://jobicy.com/jobs-rss-feed
    private static final String MAIN_URI = "https://jobicy.com/api/v2/remote-jobs";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    public static void main(String[] args) {
        String getConnection = createGetConnection(MAIN_URI + '?' + getRequestParams());
        try {
            RemoteJobDetails jobDetails = OBJECT_MAPPER.readValue(getConnection, RemoteJobDetails.class);
            System.out.println(jobDetails);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String createGetConnection(String uri) {
        HttpURLConnection connection = null;
        try {
            connection = getHttpURLConnectionWith(uri);
            connection.setRequestMethod("GET");

            return getResponseMsg(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String getRequestParams() {
        return new ApiRequestParams()
                .withGeo(ApiParamGeo.POLAND)
                .withIndustry(ApiParamIndustry.DEV)
                .toStringParams();
    }

    private static String getResponseMsg(final HttpURLConnection connection) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
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
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        return connection;
    }
}