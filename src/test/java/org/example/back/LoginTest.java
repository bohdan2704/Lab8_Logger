package org.example.back;

import org.example.login.LoanUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LoginTest {

    private JsonProcessing mockJsonManager = Mockito.mock(JsonProcessing.class);

    @Test
    void testSuccessfulLogin() {
        // Arrange
        String input = "john_doe\npassword\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        LoanUser mockUser = new LoanUser("john_doe", "hashed_password");
        when(mockJsonManager.checkLogin("john_doe", "hashed_password")).thenReturn(mockUser);

        // Act
        Login login = new Login(mockJsonManager);

        // Assert
        assertNotNull(login.getCurrentUser());
        assertEquals(mockUser, login.getCurrentUser());

        // Clean up
        System.setIn(System.in);
    }

    @Test
    void testUnsuccessfulLogin() {
        // Arrange
        String input = "unknown_user\npassword\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        when(mockJsonManager.checkLogin("unknown_user", "hashed_password")).thenReturn(null);

        // Act
        Login login = new Login(mockJsonManager);

        // Assert
        assertNull(login.getCurrentUser());

        // Clean up
        System.setIn(System.in);
    }

    @Test
    void testRegistrationOnUnsuccessfulLogin() {
        // Arrange
        String input = "unknown_user\npassword\nnew_user\nnew_password\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        when(mockJsonManager.checkLogin("unknown_user", "hashed_password")).thenReturn(null);
        LoanUser mockUser = new LoanUser("new_user", "hashed_new_password");
        when(mockJsonManager.checkLogin("new_user", "hashed_new_password")).thenReturn(mockUser);

        // Act
        Login login = new Login(mockJsonManager);

        // Assert
        assertNotNull(login.getCurrentUser());
        assertEquals(mockUser, login.getCurrentUser());

        // Clean up
        System.setIn(System.in);
    }
}
