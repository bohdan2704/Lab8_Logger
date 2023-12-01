package org.example.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.back.Hashing;
import org.example.money.Loan;
import org.example.money.TakenLoan;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LoanUser {
    private static final Logger logger = LogManager.getLogger(LoanUser.class);

    private static int maxSizeOfLoans = 3;
    private String name;
    private String password;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String address;
    private double yearIncome;
    private double regularMonthlyIncome;
    private String workPlace; // UNIVERSITY, NOT EMPLOYED
    private List<TakenLoan> loanHistory;

    //    public LoanUser() {
//    }
    public LoanUser(Scanner scanner) {
        logger.info("Creating LoanUser instance with user input.");
        readUserInput(scanner);
        loanHistory = new LinkedList<>();
    }

    public LoanUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private void readUserInput(Scanner scanner) {
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
        System.out.print("Enter your phone number: ");
        phoneNumber = scanner.nextLine();
        System.out.print("Enter your email: ");
        email = scanner.nextLine();
        System.out.print("Enter your birthday: ");
        dateOfBirth = scanner.nextLine();
        System.out.print("Enter your address: ");
        address = scanner.nextLine();
        System.out.print("Enter your income from the previous year: ");
        yearIncome = scanner.nextDouble();
        scanner.nextLine(); // Remove Enter from the input buffer
        System.out.print("Enter your regular salary: ");
        regularMonthlyIncome = scanner.nextDouble();
        scanner.nextLine(); // Remove Enter from the input buffer
        System.out.print("Enter your work place: ");
        workPlace = scanner.nextLine();
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
}
