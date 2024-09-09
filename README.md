# Merkle Tree
Merkle Tree Project

Overview

This project implements a Merkle Tree using a singly linked list structure. A Merkle Tree is a binary tree where each leaf node contains a hash of a data block, and each non-leaf node contains a hash of its children. This type of tree is fundamental in various applications, such as blockchain and file verification.

The project:

	1.	Reads a file with UTF-8 encoded text lines.
	2.	Each line is stored in a node on a singly linked list.
	3.	Cryptographic hashes (using SHA-256) of each node are computed to build the Merkle Tree.
	4.	If there is an odd number of nodes, the last node is duplicated to ensure all levels of the tree have an even number of nodes.
	5.	The Merkle root is computed, which is the hash of the root node of the Merkle Tree.

Features

	•	Merkle Tree Construction: Builds a Merkle Tree from the file’s lines.
	•	SHA-256 Hashing: Uses SHA-256 to hash nodes and concatenate child hashes to form parent nodes.
	•	Merkle Root Calculation: Computes and outputs the Merkle root.
	•	File Input: Reads data from a file and processes each line as an individual block in the Merkle Tree.
	•	Error Handling: Gracefully handles odd numbers of nodes by duplicating the last node when needed.
	•	Logger Integration: Uses SLF4J and Logback for structured logging.
	•	Unit Tested: The project includes JUnit test cases to ensure correctness.

Requirements

	•	Java 8 or above
	•	Maven for dependencies
	•	SLF4J and Logback for logging
	•	JUnit 5 for testing

Project Structure

```markdown
MerkleTreeProject/
│
├── src
│   ├── main
│   │   └── java
│   │       ├── edu
│   │       │   ├── cmu
│   │       │   │   └── andrew
│   │       │   │       └── mpanindr
│   │       │   │           └── MerkleTree.java   # Main Merkle Tree implementation
│   │       └── edu
│   │           └── utils
│   │               └── HashUtil.java              # Utility for hashing
│   └── test
│       └── java
│           └── edu
│               └── cmu
│                   └── andrew
│                       └── mpanindr
│                           └── MerkleTreeTest.java # Unit tests for Merkle Tree implementation
├── README.md
├── pom.xml                                       # Maven build file with dependencies
└── logback.xml                                   # Logback configuration for logging
```
Dependencies

Add the following dependencies to your pom.xml file to include the required libraries:

```markdown
<dependencies>
    <!-- SLF4J API for logging -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.30</version>
    </dependency>

    <!-- Logback for logging implementation -->
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.3</version>
    </dependency>

    <!-- JUnit 5 for unit testing -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.7.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.7.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Logging Configuration

The project uses Logback for logging. Below is the configuration for logback.xml:

```markdown
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="debug">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

Usage

Clone the Repository:
```markdown
git clone https://github.com/yourusername/MerkleTreeProject.git
cd MerkleTreeProject
```

Compile and Run:
Use Maven to compile and run the project.
```markdown
mvn clean compile
mvn exec:java -Dexec.mainClass="edu.cmu.andrew.mpanindr.MerkleTree"
```

Input:
The program will ask for the filename containing the text data.
```markdown
Enter the file name:
smallFile.txt
```

Sample Output:
```markdown
Enter the file name:
smallFile.txt
Merkle Root: BE263C0044B95044951327B0D9ABBD7E4E3719CC1AE59B57DF059945616219C1
```

How It Works

	1.	Reading the File:
The file is read line-by-line, and each line is added to a singly linked list.
2.	Hashing Each Node:
After storing each line, a cryptographic hash (SHA-256) is computed for each node and added to another list.
3.	Merkle Tree Construction:
•	If the number of nodes is odd, the last node is duplicated.
•	Pairwise, the hashes are concatenated and re-hashed to compute the parent node, forming the next level of the tree.
•	This process repeats until only one node remains, which is the Merkle Root.
4.	Merkle Root:
The Merkle root is the top hash, representing the integrity of the entire file.

JUnit Testing

The project includes unit tests written with JUnit 5 to ensure the correctness of the Merkle Tree construction and the cryptographic operations.
```markdown
// Sample JUnit Test
@Test
void testComputeMerkleRoot() throws NoSuchAlgorithmException {
    SinglyLinkedList list = new SinglyLinkedList();
    list.addAtEndNode("Block 1");
    list.addAtEndNode("Block 2");

    String expectedRoot = "D8A..."  // Replace with the correct expected value
    String merkleRoot = MerkleTree.computeMerkleRoot(list);

    assertEquals(expectedRoot, merkleRoot);
}
```

To run the tests:
```markdown
mvn test
```

Contribution

Feel free to open issues, submit pull requests, or contribute by expanding the project with additional features.

Acknowledgments

	•	Wikipedia: Merkle Tree
	•	Logback
	•	JUnit 5