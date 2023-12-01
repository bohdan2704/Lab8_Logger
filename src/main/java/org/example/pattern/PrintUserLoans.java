package org.example.pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.login.LoanUser;
import org.example.money.TakenLoan;

public class PrintUserLoans implements Command {
    private static final Logger logger = LogManager.getLogger(PrintUserLoans.class);

    private final LoanUser currentUser;

    public PrintUserLoans(LoanUser loanUser) {
        logger.info("Creating PrintUserLoans instance.");

        this.currentUser = loanUser;
    }

    @Override
    public void execute() {
        logger.info("Executing PrintUserLoans command.");

        try {
            var userLoans = currentUser.getUserLoans();

            if (userLoans.isEmpty()) {
                logger.info("No loans found for the user.");
                System.out.println("You don't have any loans.");
            } else {
                System.out.println("Here are all your loans: ");
                logger.info("Printing user loans.");

                int i = 1;
                for (TakenLoan takenLoan : userLoans) {
                    System.out.printf("%d. ", i);
                    System.out.println(takenLoan);
                    logger.info("Loan {}: {}", i, takenLoan);
                    i++;
                }
            }
        } catch (Exception e) {
            // Log an error message with the exception
            logger.error("An error occurred while printing user loans.", e);
            System.out.println("An error occurred while printing user loans.");
        }
    }
}
