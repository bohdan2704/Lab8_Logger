package org.example.back;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.login.LoanUser;

import java.util.Scanner;

public class Login {
    private static final Logger logger = LogManager.getLogger(Login.class);

    private Scanner scanner;
    private LoanUser currentUser;
    private JsonProcessing jsonManager;

    public Login(Scanner scanner, JsonProcessing jsonManager) {
        this.scanner = scanner;
        this.jsonManager = jsonManager;
        execute();
    }

    public LoanUser getCurrentUser() {
        return currentUser;
    }

    public void execute() {
        System.out.println("Logging, please provide username and password: ");
        logger.info("Logging, please provide username and password: ");

        System.out.print("Username: ");
        logger.info("Enter username");
        String name = scanner.nextLine();

        System.out.print("Password: ");
        logger.info("Enter password");

        String password = scanner.nextLine();
        Hashing hashing = new Hashing(password, name);
        password = hashing.SHA256();

        // CHECK IF OBJECT EXISTS
        currentUser = jsonManager.checkLogin(name, password);
        if (currentUser != null) {
            System.out.println("You are successfully logged in");
            logger.info("User {} successfully logged in", name);
        } else {
            System.out.println("Not logged in, please restart the program or proceed to registration");
            logger.info("User {} not logged in, proceeding to registration", name);
            currentUser = new LoanUser(scanner);
            jsonManager.addUser(currentUser);
            jsonManager.saveData();
        }
    }
}
