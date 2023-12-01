package org.example.back;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.data.Bank;
import org.example.login.LoanUser;
import org.example.pattern.*;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Logger logger = LogManager.getLogger(Menu.class);
    private final Scanner scanner;
    private final LoanUser loanUser;
    private final List<Bank> banks;
    private final JsonProcessing jsonManager;
    private Command command;

    public Menu(String jsonPath) {
        this.scanner = new Scanner(System.in);
        this.jsonManager = new JsonProcessing(jsonPath);
        this.banks = jsonManager.getBankList();
        this.command = null;

        Login loginCommand = new Login(scanner, jsonManager);
        this.loanUser = loginCommand.getCurrentUser();
    }

    public void run() {
        while (true) {
            System.out.println("Enter your command (see /help): ");
            logger.info("Enter your command (see /help): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "/help":
                    System.out.println("/loans -- see all available loans");
                    System.out.println("/bestloan -- pick the best option in the given interval");
                    System.out.println("/info -- information when you should pay");
                    System.out.println("/pay -- pay for the chosen loan");
                    System.out.println("/print -- print all your unpaid loans");
                    System.out.println("/take -- assign one more loan");
                    System.out.println("/help -- all commands");

                    System.out.println("/exit -- stop program execution");
                    logger.info("/exit -- stop program execution");
                    break;
                case "/loans":
                    command = new AllLoans(banks);
                    break;
                case "/bestloan":
                    command = new FindBestLoan(scanner, banks);
                    break;
                case "/info":
                    command = new InfoAboutPays(scanner, loanUser);
                    break;
                case "/pay":
                    command = new PayLoanCommand(scanner, loanUser);
                    break;
                case "/print":
                    command = new PrintUserLoans(loanUser);
                    break;
                case "/take":
                    command = new TakeLoan(jsonManager, loanUser);
                    break;
                case "/exit":
                    return;
                default:
                    System.out.println("Unknown command");
                    logger.warn("Unknown command: {}", choice);
            }
            if (command != null) {
                command.execute();
                command = null;
            }
        }
    }
}
