package org.example.management;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.money.Loan;

import java.util.LinkedList;
import java.util.List;

public class Top {
    private static final Logger logger = LogManager.getLogger(Top.class);

    private int size;
    private List<String> banks;
    private List<Loan> loans;
    private List<Double> bankProfits;

    public Top(int size) {
        this.size = size;
        this.banks = new LinkedList<>();
        this.loans = new LinkedList<>();
        this.bankProfits = new LinkedList<>();
    }

    public void insertOrIgnore(String bank, Loan loan, double bankProfit) {
        logger.info("Inserting or ignoring entry in Top.");

        // OR YOU CAN USE BINARY SEARCH FROM COLLECTIONS AND INSERT IT TO THREE ARRAYS AND EVERYTHING WILL BE EVEN BETTER
        for (int i = banks.size() - 1; i >= 0; i--) {
            if (bankProfit >= bankProfits.get(i)) {
                // Insert elements at position (i + 1)
                banks.add(i + 1, bank);
                loans.add(i + 1, loan);
                bankProfits.add(i + 1, bankProfit);
                break; // Break the loop since you've inserted the element
            }
        }

        // If the element hasn't been inserted yet (i.e., it's the smallest), insert it at the beginning.
        if (banks.isEmpty()) {
            banks.add(0, bank);
            loans.add(0, loan);
            bankProfits.add(0, bankProfit);
        }

        logger.info("Entry inserted or ignored: Bank={}, Loan={}, BankProfit={}", bank, loan, bankProfit);
    }

    @Override
    public String toString() {
        logger.info("Converting Top to String.");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            // STRING APPENDING AND CONCATENATION
            builder.append(banks.get(i)).append(loans.get(i)).append(" Actual percent: ").append(String.format("%.2f%%\n", bankProfits.get(i)));
        }
        return builder.toString();
    }
}
