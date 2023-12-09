package org.example.pattern;

import org.example.back.JsonProcessing;
import org.example.data.Bank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllLoansTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream("".getBytes());

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
        JsonProcessing jsonProcessing = new JsonProcessing("src/main/resources/dataForLoginTest/users.json", "src/main/resources/dataForLoginTest/banks.json");
        jsonProcessing.loadData();
        List<Bank> banks = jsonProcessing.getBankList();
        AllLoans allLoans = new AllLoans(banks);

        // Act
        allLoans.execute();

        // Assert
        String expectedOutput = "Here is all available loans. You can see the best option using /bestloan" + System.lineSeparator() +
                "1. -- Monobank" + System.lineSeparator() +
                "\t1. -- Loan A This is a loan for a car -- 5.5%" + System.lineSeparator() +
                "\t2. -- Loan B Home improvement loan -- 4.8%" + System.lineSeparator() +
                "\t3. -- Loan C Education loan -- 6.2%" + System.lineSeparator() +
                "2. -- Bank XYZ" + System.lineSeparator() +
                "\t1. -- Loan X Personal loan -- 4.0%" + System.lineSeparator() +
                "\t2. -- Loan Y Home mortgage -- 5.2%" + System.lineSeparator() +
                "\t3. -- Loan Z Small business loan -- 3.8%" + System.lineSeparator();
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(expectedOutput, actualOutput);
    }
}
