//Andrew ID: mpanindr
//Name: Manjunath K P

package edu.cmu.andrew.mpanindr;

import edu.utils.HashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The MerkleTree class demonstrates the construction and computation of a Merkle root
 * using a SinglyLinkedList data structure. It allows the user to either compute the
 * Merkle root for a list of predefined files or to select a single file.
 * <p>
 * The class leverages cryptographic hashing to generate the Merkle root, ensuring data
 * integrity and validation, commonly used in blockchain systems.
 * <p>
 * Features:
 * - Reads data from a file into a SinglyLinkedList, where each line becomes a node.
 * - Computes the Merkle root by iteratively hashing pairs of nodes until a single root is reached.
 * - Provides flexibility for processing either all predefined files or a user-selected file.
 * - Implements exception handling to ensure robustness when dealing with invalid inputs or errors during file processing.
 * <p>
 * Example Usage:
 * - The program prompts the user for input, either processing all files or a specific file.
 * - The Merkle root for each file is computed and displayed using SLF4J logging.
 * <p>
 * Pre-condition:
 * - The files to be processed must exist and be readable. The file names must be correct.
 * - The input list for the Merkle root computation must contain at least one node.
 * <p>
 * Post-condition:
 * - A cryptographic Merkle root is generated for the data, which can be used to verify data integrity.
 * <p>
 * Time Complexity:
 * - The file reading method operates in O(n), where n is the number of lines in the file.
 * - The Merkle root computation operates in O(n log n), where n is the number of nodes in the list.
 * <p>
 * Note:
 * - This class is intended for educational purposes and may not be optimized for high-performance use cases.
 * - The class assumes that the data can be read from files, and appropriate error handling is included for invalid file paths or content.
 * <p>
 * Author: Manjunath K P
 * Andrew ID: mpanindr
 */
public class MerkleTree {

    private static final Logger logger = LoggerFactory.getLogger(MerkleTree.class);

    public static void main(String[] args) {
        // Create a scanner to handle user input
        Scanner scanner = new Scanner(System.in);

        // Log an initial prompt asking the user whether they want to process all files or select a specific file
        logger.info("Should I get the Merkle root for all files or do you want to select a file?\n1. All files\n2. I want to select the file");

        // Read the user choice as an integer
        int choice = scanner.nextInt();

        List<String> fileNames = new ArrayList<>();
        String fileName = "";

        // Handle user's choice:
        // 1. If they choose to select a specific file, prompt for the filename.
        // 2. If they choose all files, prepare a list of predefined file names.
        if (choice == 2) {
            logger.info("Enter the file name:");
            fileName = scanner.nextLine();  // Get the specific file name from the user
        } else if (choice == 1) {
            // Add predefined file names to the list for processing all files
            fileNames = new ArrayList<>(Arrays.asList("CrimeLatLonXY.csv", "CrimeLatLonXY1990_Size2.csv", "CrimeLatLonXY1990_Size3.csv", "smallFile.txt"));
        } else {
            // If the choice is invalid, throw an exception
            throw new IllegalArgumentException("Invalid input choice: " + choice);
        }

        try {
            // If a single file was selected, process it and compute its Merkle root
            if (fileNames.isEmpty()) {
                SinglyLinkedList lineList = readFileLines(fileName);
                String merkleRoot = computeMerkleRoot(lineList);
                logger.info("Merkle Root: {}", merkleRoot);
            }
            // Otherwise, process all files and compute their respective Merkle roots
            else {
                for (String file : fileNames) {
                    SinglyLinkedList lineList = readFileLines(file);
                    String merkleRoot = computeMerkleRoot(lineList);
                    logger.info("Merkle Root for {}: {}", file, merkleRoot);
                }
            }

        } catch (IOException | NoSuchAlgorithmException e) {
            // Log any errors encountered during file reading or hashing
            logger.error(e.getMessage());
        }
    }

    /**
     * Reads a file line by line and stores each line as a node in a SinglyLinkedList.
     *
     * @param fileName The name of the file to read.
     * @return A SinglyLinkedList containing the file's lines, where each node holds a line.
     * @throws IOException If the file cannot be read.
     * @pre-condition fileName must be a valid string representing a file path,
     * and the file should exist and be readable.
     * If the file is empty, the returned list will also be empty.
     * @post-condition The method returns a SinglyLinkedList where each node contains
     * one line from the file in the order they appear. If the file is empty,
     * the list will be empty.
     * @time-complexity O(n) where n is the number of lines in the file.
     * Each line is read once, and adding each line to the SinglyLinkedList is O(1).
     */
    public static SinglyLinkedList readFileLines(String fileName) throws IOException {
        SinglyLinkedList list = new SinglyLinkedList();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.addAtEndNode(line);  // Add each line as a node
            }
        }
        return list;
    }

    /**
     * Computes the Merkle root from the given SinglyLinkedList of lines.
     *
     * @param list The SinglyLinkedList containing the lines to compute the Merkle root from.
     * @return The computed Merkle root as a String.
     * @throws NoSuchAlgorithmException If the hashing algorithm is not available.
     * @throws IllegalArgumentException If the input list is empty.
     * @pre-condition The input list must be a valid SinglyLinkedList containing at least one node.
     * If the list is empty, an IllegalArgumentException will be thrown.
     * @post-condition The method returns a single Merkle root hash, representing the root of the
     * Merkle tree computed from the given list of lines.
     * If the number of nodes in the list is odd, the last node will be duplicated
     * to ensure all levels have an even number of nodes.
     * @time-complexity O(n log n) where n is the number of nodes in the initial list.
     * - In each level, the number of nodes is halved after pairing and hashing.
     * - Hashing a pair is O(1), so the total time is O(n log n) due to the recursive
     * nature of the tree.
     */
    public static String computeMerkleRoot(SinglyLinkedList list) throws NoSuchAlgorithmException {

        // Check if the list is empty
        if (list.countNodes() == 0) {
            throw new IllegalArgumentException("List is empty. Cannot compute Merkle root.");
        }

        // If the list has an odd number of nodes, duplicate the last node
        if (list.countNodes() % 2 != 0) {
            list.duplicateLastNode();
        }

        SinglyLinkedList currentLevel = new SinglyLinkedList();

        // Hash each element in the initial list
        for (int i = 0; i < list.countNodes(); i++) {
            String hashString = HashUtil.hash(list.getObjectAt(i).toString());
            currentLevel.addAtEndNode(hashString);
        }

        // Repeat until we get to a single root hash
        while (currentLevel.countNodes() > 1) {

            // Duplicate the last node if the number of nodes is odd
            if (currentLevel.countNodes() % 2 != 0) {
                currentLevel.duplicateLastNode();
            }

            SinglyLinkedList nextLevel = new SinglyLinkedList();

            // Combine adjacent pairs of nodes and hash them
            for (int i = 0; i < currentLevel.countNodes(); i += 2) {
                String leftHash = currentLevel.getObjectAt(i).toString();
                String rightHash = currentLevel.getObjectAt(i + 1).toString();

                // Concatenate and hash the pair of nodes
                String combinedHash = HashUtil.hash(leftHash + rightHash);
                nextLevel.addAtEndNode(combinedHash);
            }

            // Move to the next level
            currentLevel = nextLevel;
        }

        // The root is the only element left
        return currentLevel.getObjectAt(0).toString();
    }
}