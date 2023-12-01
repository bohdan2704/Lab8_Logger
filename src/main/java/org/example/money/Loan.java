package org.example.money;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Loan {
    private static final Logger logger = LogManager.getLogger(Loan.class);

    private double percent;
    private int interval; // IN MONTHS DURATION OF CONTRACT
    private int paysAmount; // HOW MANY TIMES USER WILL PAY
    private String name;
    private String description;

    public Loan(double percent, int interval, int paysAmount, String name, String description) {
        this.percent = percent;
        this.interval = interval;
        this.paysAmount = paysAmount;
        this.name = name;
        this.description = description;

        logger.info("Creating Loan instance: {} -- {} -- {} months -- {} pays -- {}%", name, description, interval, paysAmount, percent * 100);
    }

    public int getInterval() {
        return interval;
    }

    public int getPaysAmount() {
        return paysAmount;
    }

    public double getPercent() {
        return percent;
    }

    public String getName() {
        return name;
    }

    public double calcBankProfit(double totalAmount) {
        logger.info("Calculating bank profit for Loan: {} -- {} -- {} months -- {} pays -- {}%", name, description, interval, paysAmount, percent * 100);

        double bankProfit = 0;
        double partOfAmount = totalAmount / paysAmount;

        // IF WE ARE USING ORDINARY CREDIT
        if (paysAmount == 1) {
            double simpleProfit = totalAmount * percent * interval;
            logger.info("Simple profit calculated: {}", simpleProfit);
            return simpleProfit;
        }

        for (int i = 0; i < interval; i++) {
            bankProfit += totalAmount * percent;

            // FORMULA THAT HELPS CALCULATE IN WHICH MONTH I WILL BE PAYING
            if (i % (interval / (paysAmount - 1)) == 0) {
                totalAmount -= partOfAmount;
                //System.out.println(i);
            }
        }

        logger.info("Bank profit calculated: {}", bankProfit);
        return bankProfit;
    }

    @Override
    public String toString() {
        return name + " " + description + " -- " + percent * 100 + "%";
    }
}
