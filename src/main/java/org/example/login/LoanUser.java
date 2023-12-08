package org.example.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.back.Hashing;
import org.example.money.Loan;
import org.example.money.TakenLoan;

import java.time.LocalDate;
import java.util.*;

public class LoanUser {
    private static final Logger logger = LogManager.getLogger(LoanUser.class);

    private static int maxSizeOfLoans = 3;
    private String name;
    private String password;
    private String address;
    private double yearIncome;
    private List<TakenLoan> loanHistory;

    public LoanUser(boolean notDefaultConstructor) {
        logger.info("Creating LoanUser instance with user input.");
        readUserInput();
        loanHistory = new ArrayList<>();
    }

    public LoanUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private void readUserInput() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Reading user input for LoanUser.");

        System.out.print("Enter your name: ");
        logger.info("Prompting user for name.");
        name = scanner.nextLine();

        System.out.print("Enter your password: ");
        logger.info("Prompting user for password.");

        password = scanner.nextLine();
        Hashing hashing = new Hashing(password, name);
        password = hashing.SHA256();

        // WE ALREADY HAVE THAT INFORMATION PASSED
        System.out.print("Enter your address: ");
        address = scanner.nextLine();
        System.out.print("Enter your income from the previous year: ");
        yearIncome = scanner.nextDouble();
        scanner.nextLine(); // Remove Enter from the input buffer
    }

    public void takeLoan(double sum, Loan loan, String bank) {
        logger.info("User {} is taking a loan.", name);

        String loanDate = LocalDate.now().toString();
        TakenLoan takenLoan = new TakenLoan(sum, loanDate, loan, bank);
        loanHistory.add(takenLoan);

        logger.info("Loan taken: {}", takenLoan);
    }

    public List<TakenLoan> getUserLoans() {
        return loanHistory;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanUser loanUser)) return false;
        return Double.compare(yearIncome, loanUser.yearIncome) == 0 && Objects.equals(getName(), loanUser.getName()) && Objects.equals(getPassword(), loanUser.getPassword()) && Objects.equals(address, loanUser.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPassword(), address, yearIncome);
    }
}
