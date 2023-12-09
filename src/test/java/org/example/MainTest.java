package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class MainTest {

    @BeforeAll
    public static void setUp() {
        // Any setup logic before tests, if needed
    }

    @Test
    public void testMainMethod() {
        // Ensure that the main method can be executed without throwing an exception
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

    @AfterAll
    public static void tearDown() {
        // Any cleanup logic after tests, if needed
    }
}
