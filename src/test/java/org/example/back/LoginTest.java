package org.example.back;

import org.example.login.LoanUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginTest {

    private ByteArrayInputStream testIn;

    @Test
    void testSuccessfulLogin() {
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;

        try {
            String input = "Bohdan\n12345\n";
            testIn = new ByteArrayInputStream(input.getBytes());
            System.setIn(testIn);

            // Create a ByteArrayOutputStream to capture the output
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // Create a new PrintStream with the ByteArrayOutputStream
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            // Save the original System.out
            // Set the System.out to the new PrintStream
            System.setOut(printStream);

            Login login = new Login(new JsonProcessing("src/main/resources/dataForLoginTest/users.json", "src/main/resources/dataForLoginTest/banks.json"));

            // Retrieve the captured output as a String
            String capturedOutput = byteArrayOutputStream.toString();
            assertEquals("Logging, please provide username and password: " + System.lineSeparator() +
                            "Username: Password: You are successfully logged in" + System.lineSeparator(),
                    capturedOutput);

        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }

    @Test
    void testBadPassword() {
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;

        try {
            String input = "Bohdan\nBadPassword\n";
            testIn = new ByteArrayInputStream(input.getBytes());
            System.setIn(testIn);

            // Create a ByteArrayOutputStream to capture the output
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // Create a new PrintStream with the ByteArrayOutputStream
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            // Save the original System.out
            // Set the System.out to the new PrintStream
            System.setOut(printStream);

            Login login = new Login(new JsonProcessing("src/main/resources/dataForLoginTest/users.json", "src/main/resources/dataForLoginTest/banks.json"));
            System.setOut(originalOut);
            System.setIn(originalIn);

            // Retrieve the captured output as a String
            String capturedOutput = byteArrayOutputStream.toString();
            assertEquals("Logging, please provide username and password: " + System.lineSeparator() +
                    "Username: Password: Password is incorrect", capturedOutput.split("\\.\\.\\.")[0]);

        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }

    @Test
    void testBadLoginName() {
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;

        try {
            String input = "BadLoginName\nBadPassword\n";
            testIn = new ByteArrayInputStream(input.getBytes());
            System.setIn(testIn);

            // Create a ByteArrayOutputStream to capture the output
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // Create a new PrintStream with the ByteArrayOutputStream
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            // Save the original System.out
            // Set the System.out to the new PrintStream
            System.setOut(printStream);

            Login login = new Login(new JsonProcessing("src/main/resources/dataForLoginTest/users.json", "src/main/resources/dataForLoginTest/banks.json"));
            System.setOut(originalOut);
            System.setIn(originalIn);

            // Retrieve the captured output as a String
            String capturedOutput = byteArrayOutputStream.toString();
            assertEquals("Logging, please provide username and password: " + System.lineSeparator() +
                    "Username: Password: Username is not found", capturedOutput.split("\\.\\.\\.")[0]);

        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }
}
