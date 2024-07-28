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
        String requestParams = new ApiRequestParams()
                .withGeo(ApiParamGeo.POLAND)
                .withIndustry(ApiParamIndustry.DEV)
                .toStringParams();

        String uri = MAIN_URI + '?' + requestParams;

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json; utf-8")
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }

        String result = response.body();

        RemoteJobDetails jobDetails;
        try {
            jobDetails = OBJECT_MAPPER.readValue(result, RemoteJobDetails.class);
            System.out.println(jobDetails);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}