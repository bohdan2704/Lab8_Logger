package org.example.back;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateToStringTest {

    @Test
    void testDateToStringAndPlusMonth() {
        // Input date in the format "yyyy-MM-dd"
        String inputDate = "2022-01-15";

        // Number of months to add
        int monthsToAdd = 3;

        // Expected result: "2022-04-15" (original date + 3 months)
        String expectedResult = "2022-04-15";

        // Call the method to be tested
        String result = DateToString.dateToStringAndPlusMonth(inputDate, monthsToAdd);

        // Assert that the result matches the expected result
        assertEquals(expectedResult, result);
    }

    @Test
    void testInvalidDate() {
        // Input an invalid date
        String invalidDate = "invalid-date";

        // Number of months to add (not used in this case)
        int monthsToAdd = 3;

        // Call the method to be tested and expect an exception
        assertThrows(RuntimeException.class, () -> DateToString.dateToStringAndPlusMonth(invalidDate, monthsToAdd));
    }
}