package org.example.pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.data.Bank;
import org.example.management.Top;
import org.example.money.Loan;

import java.util.List;
import java.util.Scanner;

public class FindBestLoan implements Command {
    private static final Logger logger = LogManager.getLogger(FindBestLoan.class);

    private int fromInterval;
    private int toInteval;
    private List<Bank> banks;

    public FindBestLoan(Scanner scanner, List<Bank> banks) {
        logger.info("Creating FindBestLoan instance.");

        System.out.print("Enter from which interval carry out search (in month): ");
        this.fromInterval = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter to which interval carry out search (in month): ");
        this.toInteval = scanner.nextInt();
        scanner.nextLine();
        this.banks = banks;
    }

    @Override
    public void execute() {
        logger.info("Executing FindBestLoan command.");

        Top topProposals = new Top(3);
        int pos = 0;
        for (Bank bank : banks) {
            for (Loan loan : bank.getPropositions()) {
                if (fromInterval <= loan.getInterval() && loan.getInterval() <= toInteval) {
                    logger.info("Checking loan: Bank={}, Loan={}, Interval={}", bank.getName(), loan, loan.getInterval());

                    double bankProfit = loan.calcBankProfit(10);
                    // INSERT AND WE GET PERFECTLY SORTED ARRAY
                    topProposals.insertOrIgnore(bank.getName(), loan, bankProfit);
                }
            }
        }
        System.out.println(topProposals);
    }
}
