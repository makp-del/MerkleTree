package edu.utils;

import org.junit.jupiter.api.Test;
import java.security.NoSuchAlgorithmException;
import static org.junit.jupiter.api.Assertions.*;

class HashUtilTest {

    // Test hashing a normal string
    @Test
    void testHashNormalString() throws NoSuchAlgorithmException {
        String input = "Hello, World!";
        String hash = HashUtil.hash(input);

        // SHA-256 hash of "Hello, World!" is known
        String expectedHash = "DFFD6021BB2BD5B0AF676290809EC3A53191DD81C7F70A4B28688A362182986F";
        assertEquals(expectedHash, hash);
    }

    // Test hashing an empty string (should throw an exception)
    @Test
    void testHashEmptyString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HashUtil.hash("");
        });

        String expectedMessage = "Input to hash cannot be null or empty.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test hashing a null value (should throw an exception)
    @Test
    void testHashNullString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HashUtil.hash(null);
        });

        String expectedMessage = "Input to hash cannot be null or empty.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test hashing a long string
    @Test
    void testHashLongString() throws NoSuchAlgorithmException {
        String input = "This is a very long string to test hashing. ".repeat(10);  // Repeats the string 10 times
        String hash = HashUtil.hash(input);

        // Ensure hash length is 64 characters (standard SHA-256 length in hexadecimal)
        assertEquals(64, hash.length());
    }

    // Test hashing a string with special characters
    @Test
    void testHashSpecialCharacters() throws NoSuchAlgorithmException {
        String input = "@#$%^&*()_+-=~`{}|[]\\:\";'<>?,./";
        String hash = HashUtil.hash(input);

        // Ensure hash length is 64 characters (standard SHA-256 length in hexadecimal)
        assertEquals(64, hash.length());
    }

    // Test hashing a string with numbers
    @Test
    void testHashNumericString() throws NoSuchAlgorithmException {
        String input = "1234567890";
        String hash = HashUtil.hash(input);

        // Known SHA-256 hash of "1234567890"
        String expectedHash = "C775E7B757EDE630CD0AA1113BD102661AB38829CA52A6422AB782862F268646";
        assertEquals(expectedHash, hash);
    }

    // Test hashing the same string multiple times (should return the same result)
    @Test
    void testHashConsistency() throws NoSuchAlgorithmException {
        String input = "consistentString";

        String hash1 = HashUtil.hash(input);
        String hash2 = HashUtil.hash(input);
        String hash3 = HashUtil.hash(input);

        assertEquals(hash1, hash2);
        assertEquals(hash2, hash3);
    }

    // Test hashing an empty space string
    @Test
    void testHashWhitespaceString() throws NoSuchAlgorithmException {
        String input = "   ";  // Just spaces
        String hash = HashUtil.hash(input);

        // Ensure hash length is 64 characters (standard SHA-256 length in hexadecimal)
        assertEquals(64, hash.length());
    }
}