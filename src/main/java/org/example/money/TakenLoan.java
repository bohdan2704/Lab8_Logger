package org.example.money;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.back.DateConvertion;
import org.example.login.LoanUser;

import java.text.MessageFormat;

public class TakenLoan {
    private static final Logger logger = LogManager.getLogger(TakenLoan.class);

    private double totalAmount;
    private double paidAmount;
    private String takenDate;
    private int paysLeft;
    private Loan loan;
    private String bank;

    public TakenLoan(double totalAmount, String takenDate, Loan loan, String bank) {
        this.totalAmount = loan.calcBankProfit(totalAmount) + totalAmount;
        this.paidAmount = 0;
        this.takenDate = takenDate;
        this.paysLeft = loan.getPaysAmount();
        this.loan = loan;
        this.bank = bank;

        logger.info("Creating TakenLoan instance: Bank={}, Loan={}, TotalAmount={}, TakenDate={}", bank, loan, totalAmount, takenDate);
    }

    @Override
    public String toString() {
        return MessageFormat.format("Bank: {0}\n{1}\nSum of the loan: {2} -- Date of the loan: {3}\n", bank, loan, totalAmount, takenDate);
    }

    public Loan getLoan() {
        return loan;
    }

    public String getDate() {
        return takenDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void paidToCoverLoan(double sum) {
        logger.info("Paid amount to cover loan: {}", sum);
        paidAmount += sum;
    }

    public void infoAboutPays() {
        logger.info("Displaying information about loan payments.");

        System.out.println("Here is information about your loan");
        Loan innerLoan = getLoan();
        for (int i = 0; i < innerLoan.getInterval(); i++) {
            // FORMULA THAT HELPS CALCULATE IN WHICH MONTH I WILL BE PAYING
            if (i % (innerLoan.getInterval() / (innerLoan.getPaysAmount())) == 0) {
                System.out.printf("Date of payments: %s -- %f\n", DateConvertion.execute(getDate(), i), getTotalAmount() / innerLoan.getPaysAmount());
            }
        }
        System.out.printf("Total sum to paid -- %f\n", getTotalAmount());
        System.out.printf("Already paid -- %.2f (%.2f%%)\n", getPaidAmount(), (getPaidAmount() / getTotalAmount() * 100));
    }

    public void processLoanPayment(LoanUser currentUser, double paymentAmount) {
        logger.info("Processing loan payment: Amount={}, RemainingAmount={}, PaysLeft={}", paymentAmount, getTotalAmount() - getPaidAmount(), paysLeft);

        paidToCoverLoan(paymentAmount);
        double diff = getTotalAmount() - getPaidAmount();

        if (diff <= 0) {
            System.out.printf("Your loan is covered, returning the rest %.2f₴ to your bank account...", -diff);
            logger.info("Loan fully paid. Removing the loan from the user's loans.");
            currentUser.getUserLoans().remove(this); // REMOVING PAID LOAN
        } else {
            // NO NEED TO PRINT INFO ABOUT PAID LOAN IN THIS CASE
            infoAboutPays();
        }
    }
}
