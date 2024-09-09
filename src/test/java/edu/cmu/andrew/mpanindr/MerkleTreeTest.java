package edu.cmu.andrew.mpanindr;

import edu.utils.HashUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class MerkleTreeTest {

    // Test readFileLines method with a valid file (Mocked content)

    private final InputStream systemIn = System.in; // Store the original System.in
    private ByteArrayInputStream testIn;

    @BeforeEach
    void setUpInputStream() {
        // This runs before each test
    }

    @AfterEach
    void restoreSystemIn() {
        // Restore System.in after each test
        System.setIn(systemIn);
    }

    // Helper method to provide input to System.in
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);  // Redirect System.in to test input
    }

    @Test
    void testProcessAllFilesChoice() {
        // Simulate user input "1\n" for the "All files" choice
        provideInput("1\n");

        // Call the main method to trigger the input
        MerkleTree.main(new String[]{});

        // Add any necessary assertions based on expected behavior
    }

    @Test
    void testProcessSpecificFileChoice() {
        // Simulate user input "2\nsampleFile.txt\n" for the specific file choice
        provideInput("2\nsampleFile.txt\n");

        // Call the main method to trigger the input
        MerkleTree.main(new String[]{});

        // Add any necessary assertions based on expected behavior
    }

    @Test
    void testInvalidChoiceInput() {
        // Simulate invalid user input "3\n"
        provideInput("3\n");

        // Expect an IllegalArgumentException for invalid choice
        assertThrows(IllegalArgumentException.class, () -> {
            MerkleTree.main(new String[]{});
        });
    }

    @Test
    void testReadFileLines() throws IOException {
        // Create a temporary file with mock content
        String fileName = "src/test/resources/sampleFile.txt";

        SinglyLinkedList list = MerkleTree.readFileLines(fileName);
        assertNotNull(list);
        assertEquals(3, list.countNodes());  // Assuming the file has 3 lines
    }

    // Test computeMerkleRoot with an even number of lines
    @Test
    void testComputeMerkleRootEvenNodes() throws NoSuchAlgorithmException {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode("Line 1");
        list.addAtEndNode("Line 2");
        list.addAtEndNode("Line 3");
        list.addAtEndNode("Line 4");

        // Compute Merkle root
        String merkleRoot = MerkleTree.computeMerkleRoot(list);
        assertNotNull(merkleRoot);
        assertEquals(64, merkleRoot.length());  // SHA-256 hashes are 64 characters long
    }

    // Test computeMerkleRoot with an odd number of lines
    @Test
    void testComputeMerkleRootOddNodes() throws NoSuchAlgorithmException {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode("Line 1");
        list.addAtEndNode("Line 2");
        list.addAtEndNode("Line 3");

        // Compute Merkle root
        String merkleRoot = MerkleTree.computeMerkleRoot(list);
        assertNotNull(merkleRoot);
        assertEquals(64, merkleRoot.length());  // SHA-256 hashes are 64 characters long
    }

    // Test computeMerkleRoot with a single node (root is the node itself)
    @Test
    void testComputeMerkleRootSingleNode() throws NoSuchAlgorithmException {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode("Single Line");

        // Compute Merkle root
        String merkleRoot = MerkleTree.computeMerkleRoot(list);
        assertNotNull(merkleRoot);
        assertEquals(HashUtil.hash(HashUtil.hash("Single Line") + HashUtil.hash("Single Line")), merkleRoot);
    }

    // Test readFileLines with an empty file
    @Test
    void testReadFileLinesEmptyFile() throws IOException {
        String fileName = "src/test/resources/emptyFile.txt";

        SinglyLinkedList list = MerkleTree.readFileLines(fileName);
        assertNotNull(list);
        assertEquals(0, list.countNodes());
    }

    // Test Merkle root computation with an empty list (should throw an exception)
    @Test
    void testComputeMerkleRootEmptyList() {
        SinglyLinkedList list = new SinglyLinkedList();

        // Compute Merkle root and expect an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MerkleTree.computeMerkleRoot(list);
        });

        String expectedMessage = "List is empty. Cannot compute Merkle root.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test Merkle root computation with null values (should throw an exception)
    @Test
    void testHashWithNullValues() {
        // Expect an exception when trying to hash a null value
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HashUtil.hash(null);
        });

        String expectedMessage = "Input to hash cannot be null or empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}