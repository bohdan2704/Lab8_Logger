package org.example.pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.data.Bank;
import org.example.money.Loan;

import java.util.List;

public class AllLoans implements Command {
    private static final Logger logger = LogManager.getLogger(AllLoans.class);

    private final List<Bank> banks;

    public AllLoans(List<Bank> banks) {
        this.banks = banks;
        logger.info("Creating AllLoans instance.");
    }

    @Override
    public void execute() {
        logger.info("Executing AllLoans command.");

        System.out.println("Here is all available loans. You can see the best option using /bestloan");
        int i = 1;
        for (Bank bank : banks) {
            System.out.printf("%d. -- ", i);
            i++;
            System.out.println(bank);
            int j = 1;
            for (Loan loan : bank.getPropositions()) {
                System.out.printf("\t%d. -- ", j);
                j++;
                System.out.println(loan);
            }
        }
    }
}
