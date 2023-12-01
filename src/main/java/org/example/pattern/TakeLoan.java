package org.example.pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.back.JsonProcessing;
import org.example.data.Bank;
import org.example.login.LoanUser;
import org.example.money.Loan;

import java.util.Scanner;

public class TakeLoan implements Command {
    private static final Logger logger = LogManager.getLogger(TakeLoan.class);
    private JsonProcessing jsonManager;
    private LoanUser currentUser;

    public TakeLoan(JsonProcessing jsonManager, LoanUser currentUser) {
        this.jsonManager = jsonManager;
        this.currentUser = currentUser;
    }

    @Override
    public void execute() {
        logger.info("Executing TakeLoan command.");

        System.out.println("Taking a loan? Great!");
        logger.info("Taking a loan? Great!");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Give us bank index: ");
        int bankIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Remove Enter from the input buffer
        logger.trace("Bank index entered: {}", bankIndex);

        System.out.print("Give us loan index: ");
        int loanIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Remove Enter from the input buffer
        logger.trace("Loan index entered: {}", loanIndex);

        Bank chosenBank = jsonManager.getBankList().get(bankIndex);
        Loan chosenLoan = chosenBank.getPropositions()[loanIndex];

        System.out.print("Give us loan sum: ");
        double loanSum = scanner.nextDouble();
        scanner.nextLine(); // Remove Enter from the input buffer
        logger.trace("Loan sum entered: {}", loanSum);

        // Log the details before passing
        logger.info("Loan details: Bank={}, Loan={}, LoanSum={}", chosenBank.getName(), chosenLoan, loanSum);

        // PASSING
        currentUser.takeLoan(loanSum, chosenLoan, chosenBank.getName());

        // Log success message
        jsonManager.saveData();
        logger.info("Loan successfully taken.");
        System.out.println("Loan successfully taken.");
    }
}
