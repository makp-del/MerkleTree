package edu.cmu.andrew.mpanindr;

import edu.utils.HashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class MerkleTree {

    private static Logger logger = LoggerFactory.getLogger(MerkleTree.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter the file name:");
        String fileName =  scanner.nextLine();

        try {
            // Read lines from the file
            SinglyLinkedList lineList = readFileLines(fileName);

            // Compute the Merkle root
            String merkleRoot = computeMerkleRoot(lineList);
            logger.info("Merkle Root: " + merkleRoot);

        } catch (IOException | NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }
    }

    // Read the file line by line and store in a SinglyLinkedList
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

    // Compute Merkle root from the list of lines
    public static String computeMerkleRoot(SinglyLinkedList list) throws NoSuchAlgorithmException {

        // Check if the list is empty
        if (list.countNodes() == 0) {
            throw new IllegalArgumentException("List is empty. Cannot compute Merkle root.");
        }

        // If the list has an odd number of nodes, duplicate the last node
        if (list.countNodes() % 2 != 0) {
            list.duplicateLastNode();
        }

        SinglyLinkedList currentLevel = list;

        while (currentLevel.countNodes() > 1) {
            SinglyLinkedList nextLevel = new SinglyLinkedList();
            ArrayList<String> nodes = currentLevel.getAllNodes();

            for (int i = 0; i < nodes.size(); i += 2) {
                String leftHash = HashUtil.hash(nodes.get(i));
                String rightHash = HashUtil.hash(nodes.get(i + 1));

                String combinedHash = HashUtil.hash(leftHash + rightHash);  // Concatenate and hash
                nextLevel.addAtEndNode(combinedHash);
            }

            currentLevel = nextLevel;  // Move to the next level in the tree
        }

        // The root is the only element left
        return currentLevel.getAllNodes().get(0);
    }
}