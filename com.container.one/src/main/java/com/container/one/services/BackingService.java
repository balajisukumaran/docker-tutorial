package com.container.one.services;

import com.container.one.dtos.FileInfo;
import com.container.one.dtos.SumInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class BackingService {
    public SumInfo getSum(FileInfo fileInfo) {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(fileInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://container2:8000/sum"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            SumInfo sumInfo = objectMapper.readValue(response.body(), SumInfo.class);
            return sumInfo;

        } catch (Exception e) {
            return new SumInfo(e.getMessage(), 0);
        }
    }
}
