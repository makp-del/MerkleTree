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

public class MerkleTree {

    private static Logger logger = LoggerFactory.getLogger(MerkleTree.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Should I get the merkle root for all files or do you want to select a file?\n1. All files\n2. I want to select the file");
        int choice = scanner.nextInt();

        List<String> fileNames = new ArrayList<>();

        String fileName = "";

        if(choice == 2) {
            logger.info("Enter the file name:");
            fileName =  scanner.nextLine();
        }
        else if(choice == 1){
            fileNames = new ArrayList<>(Arrays.asList("CrimeLatLonXY.csv", "CrimeLatLonXY1990_Size2.csv", "CrimeLatLonXY1990_Size3.csv", "smallFile.txt"));
        }
        else{
            throw new IllegalArgumentException("Invalid input choice: " + choice);
        }

        try {

            if(fileNames.isEmpty()){
                SinglyLinkedList lineList = readFileLines(fileName);
                String merkleRoot = computeMerkleRoot(lineList);
                logger.info("Merkle Root: {}", merkleRoot);
            }
            else{
                for(String file: fileNames){
                    SinglyLinkedList lineList = readFileLines(file);
                    String merkleRoot = computeMerkleRoot(lineList);
                    logger.info("Merkle Root for {}: {}", file, merkleRoot);
                }
            }

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

        SinglyLinkedList currentLevel = new SinglyLinkedList();

        for(int i = 0; i < list.countNodes(); i ++){
            String hashString = HashUtil.hash(list.getObjectAt(i).toString());
            currentLevel.addAtEndNode(hashString);
        }

        while (currentLevel.countNodes() > 1) {

            if(currentLevel.countNodes() % 2 != 0){
                currentLevel.duplicateLastNode();
            }

            SinglyLinkedList nextLevel = new SinglyLinkedList();

            for (int i = 0; i < currentLevel.countNodes(); i += 2) {
                String leftHash =currentLevel.getObjectAt(i).toString();
                String rightHash = currentLevel.getObjectAt(i + 1).toString();

                String combinedHash = HashUtil.hash(leftHash + rightHash);  // Concatenate and hash
                nextLevel.addAtEndNode(combinedHash);
            }

            currentLevel = nextLevel;  // Move to the next level in the tree
        }

        // The root is the only element left
        return currentLevel.getObjectAt(0).toString();
    }
}