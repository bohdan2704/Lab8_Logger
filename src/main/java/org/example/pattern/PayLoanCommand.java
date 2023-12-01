package org.example.pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.back.PickerUserLoan;
import org.example.login.LoanUser;
import org.example.money.TakenLoan;

import java.util.Scanner;

public class PayLoanCommand implements Command {
    private static final Logger logger = LogManager.getLogger(PayLoanCommand.class);

    private final Scanner scanner;
    private final PickerUserLoan pickUserLoan;
    private final LoanUser currentUser;

    public PayLoanCommand(Scanner scanner, LoanUser currentUser) {
        logger.info("Creating PayLoanCommand instance.");

        this.scanner = scanner;
        this.pickUserLoan = new PickerUserLoan(scanner, currentUser);
        this.currentUser = currentUser;
    }

    @Override
    public void execute() {
        logger.info("Executing PayLoanCommand command.");

        System.out.println("Enter index of the loan you want to pay for");
        TakenLoan takenLoan = pickUserLoan.execute();

        System.out.println("Enter the sum you will be paying to cover this loan: ");
        double sum = scanner.nextDouble();
        scanner.nextLine();

        takenLoan.processLoanPayment(currentUser, sum);
    }
}
