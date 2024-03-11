package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.example.Utils.*;

public class HttpStatusImageDownloader {
    public void downloadStatusImage(int code) throws IOException {
        String url = new HttpStatusChecker().getStatusImage(code);

        try(InputStream in = new URL(url).openStream()) {
            String path = DIRECTION_FOR_SAVE + code + EXTENSION;

            createDirectoryForSaveIfNotExists();

            File file = new File(path);
            if (file.exists()) {
                System.out.println(String.format(FILE_ALREADY_EXIST_TEXT, code));
            } else {
                Files.copy(in, Path.of(path), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image downloaded successfully.");
            }
        } catch (Exception e) {
            throw new FileNotFoundException(String.format(FILE_NOT_FOUND_EXCEPTION_TEXT, code));
        }
    }

    private void createDirectoryForSaveIfNotExists() {
        Path directoryPath = Paths.get(DIRECTION_FOR_SAVE);
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                throw new RuntimeException("Error creating directory: " + DIRECTION_FOR_SAVE, e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        HttpStatusImageDownloader httpStatusImageDownloader = new HttpStatusImageDownloader();
        httpStatusImageDownloader.downloadStatusImage(2000);
    }
}
