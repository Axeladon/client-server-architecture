package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HttpStatusCheckerTest {
    HttpStatusChecker httpStatusChecker;
    private static final int VALID_STATUS_CODE = 200;
    private static final int INVALID_STATUS_CODE = 10000;

    @BeforeEach
    void beforeEach() {
        httpStatusChecker = new HttpStatusChecker();
    }

    @Test
    void testGetStatusImageSuccess() throws IOException {
        assertEquals("https://http.cat/200.jpg", httpStatusChecker.getStatusImage(VALID_STATUS_CODE));
    }

    @Test
    void testGetStatusImageFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> {
            httpStatusChecker.getStatusImage(INVALID_STATUS_CODE);
        });
    }
}
