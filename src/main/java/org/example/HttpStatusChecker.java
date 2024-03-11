package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.example.Utils.*;

public class HttpStatusChecker {
    public String getStatusImage(int code) throws IOException {
        String url = START_URL + code + EXTENSION;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        int responseCode = connection.getResponseCode();
        connection.disconnect();

        if (responseCode == 404) {
            throw new FileNotFoundException(String.format(FILE_NOT_FOUND_EXCEPTION_TEXT, code));
        } else {
            return url;
        }
    }
}
