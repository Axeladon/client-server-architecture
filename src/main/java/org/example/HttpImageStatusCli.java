package org.example;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HttpImageStatusCli {
    void askStatus() {
        HttpStatusImageDownloader httpStatusImageDownloader = new HttpStatusImageDownloader();
        int codeStatus = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter HTTP status code: ");
            codeStatus = scanner.nextInt();
            httpStatusImageDownloader.downloadStatusImage(codeStatus);
        } catch (InputMismatchException e){
            System.out.println("Please enter valid number");
        } catch (IOException e) {
            System.out.println(String.format("Here is not image for HTTP status %s", codeStatus));
        }
    }

    public static void main(String[] args) {
        HttpImageStatusCli httpImageStatusCli = new HttpImageStatusCli();
        httpImageStatusCli.askStatus();
    }
}
