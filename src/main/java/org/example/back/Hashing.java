package org.example.back;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.xml.bind.DatatypeConverter;

public class Hashing {
    private static final Logger logger = LogManager.getLogger(Hashing.class);
    private final MessageDigest digest;
    private final String password;
    private final String salt;
    private final String stringToHash;

    public Hashing(String password, String salt) {
        this.password = password;
        this.salt = salt;
        this.stringToHash = combinePasswordAndSalt();

        // GET HASHING ALGO FROM JAVA LIB
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error while getting the instance of hash algorithm SHA-256: " + e.getMessage());
            throw new RuntimeException("Invalid hashing algorithm", e);
        }

    }

    String combinePasswordAndSalt() {
        logger.trace("Combining password and salt.");
        // MAKE MORE SECURE CONCATENATION
        return password + salt;
    }

    public String hash() {
        logger.trace("Starting [String hash256()] function");
        byte[] hashBytes = digest.digest(stringToHash.getBytes());

        // Convert the byte array to a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02X", b));
        }

        logger.trace("Ending [String hash256()]");
        return hexString.toString();
    }

    public String SHA256() {
        logger.trace("Starting [String hash256()] function");

        logger.trace("Ending [String hash256()]");
        byte[] hashBytes = digest.digest(stringToHash.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }
}
