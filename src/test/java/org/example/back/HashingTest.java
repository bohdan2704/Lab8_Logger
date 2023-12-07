package org.example.back;

import com.sun.mail.iap.ByteArray;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HashingTest {

    @Test
    void combinePasswordAndSalt() {
        Hashing hashing = new Hashing("HelloCoders", "ThisIsSalt");
        assertEquals("HelloCodersThisIsSalt", hashing.combinePasswordAndSalt());
    }

    @Test
    void hash256() {
        Hashing hashing = new Hashing("HelloCoders", "ThisIsSalt");
        assertEquals("DAD12C4E4E7618E265BE4FC7C963F594D53D8E903FDAC63008C339C632B4297B",hashing.hash());
    }

    @Test
    void SHA256() {
        Hashing hashing = new Hashing("HelloCoders", "ThisIsSalt");
        assertEquals("2tEsTk52GOJlvk/HyWP1lNU9jpA/2sYwCMM5xjK0KXs=", hashing.SHA256());
    }
}