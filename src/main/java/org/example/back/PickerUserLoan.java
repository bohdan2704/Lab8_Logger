//package org.example.back;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.example.login.LoanUser;
//import org.example.money.TakenLoan;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class PickerUserLoan {
//    private static final Logger logger = LogManager.getLogger(PickerUserLoan.class);
//
//    private Scanner scanner;
//    private LoanUser currentUser;
//
//    public PickerUserLoan(LoanUser currentUser) {
//        this.scanner = new Scanner(System.in);
//        this.currentUser = currentUser;
//    }
//
//    public TakenLoan execute() {
//        logger.info("Executing PickerUserLoan.");
//
//        System.out.print("Index can be seen /myloans: ");
//        logger.info("Prompting user for loan index.");
//
//        int creditIndex = scanner.nextInt() - 1;
//        scanner.nextLine();
//
//        // SHOULD PRINT OUT PAYMENT THAT WILL BE TAKEN EACH TIME
//        List<TakenLoan> userLoans = currentUser.getUserLoans();
//        TakenLoan chosenLoan = userLoans.get(creditIndex);
//
//        logger.info("User chose loan with index {}: {}", creditIndex, chosenLoan);
//        return chosenLoan;
//    }
//}
