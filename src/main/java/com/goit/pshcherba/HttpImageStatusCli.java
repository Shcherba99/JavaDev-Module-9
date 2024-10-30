package com.goit.pshcherba;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HttpImageStatusCli {
    private final HttpStatusImageDownloader httpStatusImageDownloader = new HttpStatusImageDownloader();

    public void askStatus()  {
        System.out.println("Enter HTTP status code");
        int code = getValidStatusCode();

        try {
            httpStatusImageDownloader.downloadStatusImage(code);
        } catch (NoSuchElementException e) {
            System.out.println("There is not image for HTTP status " + code);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private int getValidStatusCode() {
        Scanner scanner = new Scanner(System.in);
        String input;
        int code = -1;

        do {
            try {
                input = scanner.nextLine();
                code = Integer.parseInt(input);
                if (code < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid number");
            }

        } while (code < 0);

        return code;
    }
}
