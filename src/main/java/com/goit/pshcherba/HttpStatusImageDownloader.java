package com.goit.pshcherba;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class HttpStatusImageDownloader {
    private final HttpStatusChecker statusChecker = new HttpStatusChecker();

    public void downloadStatusImage(int code) throws IOException, InterruptedException {
        //Якщо картинки немає - метод викидає Exception
        String imageUrl = statusChecker.getStatusImage(code);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(imageUrl))
                .GET()
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        Path imagePath = Path.of(code + ".jpg");

        try (InputStream inputStream = response.body()) {
            Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image downloaded successfully: " + imagePath);
        }
    }
}
