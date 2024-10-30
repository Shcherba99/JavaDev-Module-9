package com.goit.pshcherba;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.NoSuchElementException;

public class HttpStatusChecker {
    public String getStatusImage(int code) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String uri = "https://http.cat/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + code + ".jpg"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (code >= 0 && code != 404 && response.statusCode() == 404) {
            throw new NoSuchElementException("Picture for status " + code + " not found.");
        }

        return String.valueOf(response.uri());
    }
}
