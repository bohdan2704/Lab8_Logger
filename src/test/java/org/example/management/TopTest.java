package org.example.management;

import org.apache.logging.log4j.core.util.Assert;
import org.example.back.JsonProcessing;
import org.example.data.Bank;
import org.example.money.Loan;
import org.example.pattern.FindBestLoan;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopTest {

    @Test
    void getBestProposition() {
        // Data
        List<Bank> banks = new ArrayList<>();

        Loan[] monoBankOffers = {
                new Loan(0.055, 12, 6, "Loan A", "This is a loan for a car"),
                new Loan(0.048, 6, 6, "Loan B", "Home improvement loan"),
                new Loan(0.062, 36, 2, "Loan C", "Education loan")
        };
        banks.add(new Bank("Monobank", "bb2b2db629700080f7504910ed328fc4", monoBankOffers));

        Loan[] bankXYZOffers = {
                new Loan(0.040, 24, 8, "Loan X", "Personal loan"),
                new Loan(0.052, 12, 4, "Loan Y", "Home mortgage"),
                new Loan(0.038, 6, 3, "Loan Z", "Small business loan")
        };
        banks.add(new Bank("Bank XYZ", "659dff57405ced87f824ed4cc232c76b", bankXYZOffers));



        String input = "2\n36\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method that reads from the console
        FindBestLoan foundBestLoan = new FindBestLoan(banks);
        foundBestLoan.execute();

        String output = outputStream.toString().trim();
        // Remove the specified substrings
        String result = output
                .replace("Enter from which interval carry out search (in month): ", "")
                .replace("Enter to which interval carry out search (in month): ", "");

        // Reset the System.in stream
        System.setIn(System.in);
        System.setOut(System.out);

        assertEquals(result,
                "MonobankLoan A This is a loan for a car -- 5.5% Actual percent: 3,30%\n" +
                "Bank XYZLoan Y Home mortgage -- 5.2% Actual percent: 3,51%\n" +
                "Bank XYZLoan X Personal loan -- 4.0% Actual percent: 4,60%");
    }
}