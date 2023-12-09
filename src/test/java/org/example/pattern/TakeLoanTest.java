package org.example.pattern;

import org.example.back.JsonProcessing;
import org.example.data.Bank;
import org.example.login.LoanUser;
import org.example.money.Loan;
import org.example.money.TakenLoan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TakeLoanTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream("1\n1\n1000\n".getBytes());

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(inputStreamCaptor);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void testExecute() {
        // Arrange
        JsonProcessing jsonProcessing = new JsonProcessing("src/main/resources/dataForLoginTest/users.json", "src/main/resources/dataForLoginTest/banks.json");
        jsonProcessing.loadData();
        List<Bank> banks = jsonProcessing.getBankList();
        LoanUser currentUser = jsonProcessing.getUserList().getFirst();
        TakeLoan takeLoan = new TakeLoan(jsonProcessing, currentUser);

        // Act
        takeLoan.execute();

        // Assert
        String expectedOutput = "Taking a loan? Great!" + System.lineSeparator() +
                "Give us bank index: Give us loan index: Give us loan sum: Loan successfully taken." + System.lineSeparator();

        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
