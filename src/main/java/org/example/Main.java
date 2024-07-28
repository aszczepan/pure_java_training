package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Main {

    //free api website: https://jobicy.com/jobs-rss-feed
    private static final String MAIN_URI = "https://jobicy.com/api/v2/remote-jobs";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    public static void main(String[] args) {
        String uri = MAIN_URI + '?' + createApiRequestParams();
        HttpRequest request = createGetRequestTo(uri);
        String result = sendGet(request);
        RemoteJobDetails jobDetails = mapJsonToObject(result);
        System.out.println(jobDetails);
    }

    private static RemoteJobDetails mapJsonToObject(String result) {
        try {
            return OBJECT_MAPPER.readValue(result, RemoteJobDetails.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String sendGet(HttpRequest request) {
        HttpClient httpClient = createHttpClient();
        try {
            return httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString())
                    .body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static HttpClient createHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    private static HttpRequest createGetRequestTo(String uri) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json; utf-8")
                .build();
    }

    private static String createApiRequestParams() {
        return new ApiRequestParams()
                .withGeo(ApiParamGeo.POLAND)
                .withIndustry(ApiParamIndustry.DEV)
                .toStringParams();
    }
}