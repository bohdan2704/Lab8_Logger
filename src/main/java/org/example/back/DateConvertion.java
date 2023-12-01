package org.example.back;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConvertion {
    private static final Logger logger = LogManager.getLogger(DateConvertion.class);

    public static String execute(String dateString, int i) {
        try {
            logger.info("Executing DateConvertion with dateString: {} and i: {}", dateString, i);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateString, formatter);
            date = date.plusMonths(i);

            String result = date.toString();
            logger.info("DateConvertion result: {}", result);
            return result;
        } catch (DateTimeParseException e) {
            // Handle the exception, e.g., log it or return a default value
            logger.error("Error converting date: {}", e.getMessage());
            throw new RuntimeException("Invalid date provided", e);
        }
    }
}
