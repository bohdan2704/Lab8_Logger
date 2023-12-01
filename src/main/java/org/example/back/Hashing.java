package org.example.back;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hashing {
    private static final Logger logger = LogManager.getLogger(Hashing.class);

    private String password;
    private String salt;

    public Hashing(String password, String salt) {
        this.password = password;
        this.salt = salt;
    }

    public String combinePasswordAndSalt() {
        logger.info("Combining password and salt.");
        // MAKE MORE SECURE CONCATENATION
        return password + salt;
    }

    public String SHA256() {
        logger.info("Executing SHA256 hashing.");

        String data = combinePasswordAndSalt();
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error creating MessageDigest: {}", e.getMessage());
            throw new RuntimeException("Invalid hashing algorithm", e);
        }

        byte[] hashBytes = digest.digest(data.getBytes());
        String result = Base64.getEncoder().encodeToString(hashBytes);

        logger.info("Hashing result: {}", result);
        return result;
    }
}
