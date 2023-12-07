// Кредити. Сформувати набір пропозицій клієнту по цільових кредитах різних банків
// для оптимального вибору. Враховувати можливість дострокового погашення
// кредиту і/або збільшення кредитної лінії. Реалізувати вибір і пошук кредиту за
// певними параметрами.
package org.example;

import org.example.back.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            String jsonPath = "src/main/data";
            Menu menu = new Menu(jsonPath);

            // Log a trace message
            logger.trace("Application started");

            // Log an info message
            logger.info("Initializing menu...");

            menu.run();

            // Log an info message
            logger.error("This is sample error to check how mailer and buffer logs work");
            logger.info("Application completed successfully");
        } catch (Exception e) {
            // Log an error message with the exception
            logger.error("An error occurred during the app work", e);
        } finally {
            scanner.close();
            // Log a trace message
            logger.trace("Application exiting");
        }
    }
}
