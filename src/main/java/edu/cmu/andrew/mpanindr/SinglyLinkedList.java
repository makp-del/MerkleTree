//Andrew ID: mpanindr
//Name: Manjunath K P

package edu.cmu.andrew.mpanindr;

import edu.colorado.nodes.ObjectNode;

import java.util.ArrayList;

/**
 * The SinglyLinkedList class represents a singly linked list where each node points to the next node in the list.
 * It supports operations such as adding nodes to the front or end, retrieving nodes by index, counting nodes,
 * and iterating through the list.
 * <p>
 * This class utilizes the ObjectNode class to represent individual nodes, which hold both the data and a link to the next node.
 * <p>
 * Key Features:
 * - Adding nodes to the front or end of the list.
 * - Counting nodes and retrieving data from specific nodes by index.
 * - Duplicating the last node of the list.
 * - Iterating through the list with methods like reset(), hasNext(), and next().
 * - Retrieving all nodes as an ArrayList of Strings for easier handling.
 * - Provides a string representation of the list with the toString() method.
 * <p>
 * Pre-condition:
 * - The data added to the list should not be null (although the class does not explicitly check this).
 * - For some operations like retrieving nodes, the index must be within valid bounds.
 * <p>
 * Post-condition:
 * - The list maintains an ordered collection of nodes, and operations like adding, retrieving, and iterating work as expected.
 * <p>
 * Time Complexity:
 * - Most operations such as adding to the front or end, or resetting the iterator are O(1) as they only modify or access single nodes.
 * - Retrieving nodes by index and iteration methods have a time complexity of O(n), where n is the number of nodes in the list.
 * - The toString() method also operates in O(n) time, as it needs to traverse all nodes to generate the string.
 * <p>
 * Example Usage:
 * - A SinglyLinkedList can be initialized and populated with data by adding nodes.
 * - The class provides convenient methods for counting nodes, retrieving nodes by index, and iterating through the list.
 * - It is commonly used for linear data structures where each element points to the next, making it efficient for ordered traversal.
 * <p>
 * Note:
 * - This implementation assumes that null values are not used as node data, although they are technically allowed.
 * - The class is designed for educational purposes and may not handle edge cases or memory optimizations typically expected in production environments.
 * <p>
 * Author: Manjunath K P
 * Andrew ID: mpanindr
 */
public class SinglyLinkedList {
    private ObjectNode head;
    private ObjectNode tail;
    private int countNodes;
    private ObjectNode iterator;

    /**
     * Constructor for the SinglyLinkedList class.
     *
     * @pre-condition No pre-existing list is required; this constructor initializes an empty singly linked list.
     * @post-condition Initializes an empty singly linked list with the head and tail set to null, the node count set to zero,
     * and the iterator initialized to null. The list is ready to accept new nodes.
     * @time-complexity O(1) - The constructor performs a constant number of operations to initialize the list.
     */
    public SinglyLinkedList() {
        head = null;
        tail = null;
        countNodes = 0;
        iterator = null;
    }

    // Test Driver: Testing with BigInteger data and a list of lists
    public static void main(String[] args) {
        // Create a new SinglyLinkedList
        SinglyLinkedList list = new SinglyLinkedList();

        char start = 'b';
        for (int i = 1; i < 24; i++) {
            list.addAtEndNode(start);
            start++;
        }

        // Test adding a node to the front of the list
        list.addAtFrontNode('a');

        // Display the list using the toString method
        System.out.println("List after adding nodes: " + list);

        // Test getting the last element
        System.out.println("Last element in the list: " + list.getLast());

        // Test counting nodes in the list
        System.out.println("Number of nodes in the list: " + list.countNodes());

        // Test getting an object at a specific index
        System.out.println("Element at index 2: " + list.getObjectAt(2));

        // Test list iteration using reset(), hasNext(), and next()
        System.out.println("Iterating over the list using iterator methods:");
        list.reset(); // Reset the iterator to the start of the list
        while (list.hasNext()) {
            System.out.print(list.next());
        }
        System.out.println();
        // Add more elements to the list to ensure the iteration works with more data
        list.addAtEndNode('y');
        list.addAtEndNode('z');

        // Display the updated list
        System.out.println("Updated list after adding more nodes: " + list);

        // Iterate again to check the iterator after adding more nodes
        System.out.println("Iterating over the updated list:");
        list.reset(); // Reset the iterator to the start of the updated list
        while (list.hasNext()) {
            System.out.print(list.next());
        }
        System.out.println();
    }

    /**
     * Method to add a node at the end of the singly linked list.
     *
     * @param c The data to be added in the new node at the end of the list.
     * @pre-condition The list may be empty or non-empty. The input data should not be null (although this is not explicitly checked).
     * @post-condition A new node with the provided data is added to the end of the list.
     * If the list was empty, both the head and tail are set to the new node. If the list was not empty,
     * the new node is linked after the current tail, and the tail is updated to the new node.
     * The count of nodes in the list is incremented.
     * @time-complexity O(1) - The method operates in constant time because it only updates the tail of the list and increments the node count.
     */
    public void addAtEndNode(Object c) {
        ObjectNode newNode = new ObjectNode(c, null);

        // Update the tail to the new node
        if (head == null) { // If the list is empty, both head and tail point to the new node
            head = newNode;
        } else {
            tail.setLink(newNode); // Link the new node after the tail
        }
        tail = newNode;

        countNodes++;
    }

    /**
     * Method to add a node at the front of the singly linked list.
     *
     * @param c The data to be added in the new node at the front of the list.
     * @pre-condition The list may be empty or non-empty. The input data should be non-null (although this is not explicitly checked).
     * @post-condition A new node with the provided data is added to the front of the list.
     * If the list was empty, the tail is set to the new node. In any case, the head is updated to the new node.
     * The count of nodes in the list is incremented.
     * @time-complexity O(1) - The method operates in constant time because it only updates the head and possibly the tail of the list.
     */
    public void addAtFrontNode(Object c) {
        ObjectNode newNode = new ObjectNode(c, head); // The new node points to the current head

        if (head == null) { // If the list is empty, set tail to the new node
            tail = newNode;
        }

        head = newNode; // Update the head to the new node
        countNodes++;
    }

    /**
     * Method to count the number of nodes in the singly linked list.
     *
     * @return The number of nodes currently in the list.
     * @pre-condition The list is either empty or contains a certain number of nodes.
     * @post-condition The method returns the current count of nodes in the list without modifying the list.
     * @time-complexity O(1) - The method operates in constant time because it simply returns the count of nodes already maintained by the list.
     */
    public int countNodes() {
        return countNodes;
    }

    /**
     * Method to get the data in the tail of the singly linked list.
     *
     * @return The data stored in the last node (tail) of the list, or null if the list is empty.
     * @pre-condition The list may be empty or non-empty.
     * @post-condition If the list is empty, the method returns null. If the list is non-empty, the method returns the data in the tail node.
     * @time-complexity O(1) - The method operates in constant time because it directly accesses the tail reference.
     */
    public Object getLast() {
        if (tail != null) {
            return tail.getData();
        }
        return null; // Return null if the list is empty
    }

    /**
     * Method to retrieve the object stored at a specific index in the singly linked list (0-based indexing).
     *
     * @param i The index of the object to retrieve, with 0 representing the first node in the list.
     * @return The data stored at the node located at the specified index.
     * @throws IndexOutOfBoundsException If the provided index is less than 0 or greater than or equal to the total number of nodes in the list.
     * @pre-condition The list contains one or more nodes, and the index i is between 0 (inclusive) and countNodes (exclusive).
     * @post-condition The method returns the data stored at the specified index without modifying the list.
     * @time-complexity O(n) - The method has linear time complexity since it may need to traverse through up to n nodes to reach the target index.
     */
    public Object getObjectAt(int i) {
        if (i < 0 || i >= countNodes) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        ObjectNode current = head;
        for (int index = 0; index < i; index++) {
            current = current.getLink();
        }

        return current.getData();
    }

    /**
     * Resets the iterator to the start of the list, pointing it to the head node.
     *
     * @pre-condition The list may be empty or non-empty.
     * @post-condition The iterator is set to the head of the list, ready for traversal from the beginning.
     * @time-complexity O(1) - The method only sets the iterator to the head of the list.
     */
    public void reset() {
        iterator = head;
    }

    /**
     * Checks if there are more nodes to traverse in the list by verifying if the iterator is not null.
     *
     * @return true if there is a next node (i.e., iterator is not null), false otherwise.
     * @pre-condition The iterator is initialized using the reset() method or is already in use for iteration.
     * @post-condition The method checks whether there are more nodes to traverse and returns the result without modifying the list or iterator.
     * @time-complexity O(1) - It simply checks whether the iterator is null or not.
     */
    public boolean hasNext() {
        return iterator != null;
    }

    /**
     * Retrieves the data from the current node pointed to by the iterator and advances the iterator to the next node.
     *
     * @return The data stored in the current node, or null if the iterator is at the end of the list.
     * @pre-condition The iterator is not null, and the list is not empty.
     * @post-condition The iterator is moved to the next node in the list. If the list is exhausted, the iterator becomes null.
     * @time-complexity O(1) - It retrieves the data and advances the iterator in constant time.
     */
    public Object next() {
        if (iterator == null) {
            return null;
        }

        Object data = iterator.getData();
        iterator = iterator.getLink();
        return data;
    }

    /**
     * Duplicates the last node in the list, adding it to the end of the list.
     *
     * @pre-condition The list contains at least one node (i.e., the list is not empty).
     * @post-condition The last node is duplicated, and the new node is added as the tail of the list.
     * @time-complexity O(1) - The method operates in constant time by setting a new tail node and updating the link.
     */
    public void duplicateLastNode() {
        ObjectNode newNode = new ObjectNode(this.tail.getData(), null);
        this.tail.setLink(newNode);
        this.tail = newNode;
        this.countNodes++;
    }

    /**
     * Retrieves all nodes from the list and returns them as an ArrayList of strings.
     *
     * @return An ArrayList containing the string representations of all nodes' data in the list.
     * @pre-condition The list may contain zero or more nodes.
     * @post-condition The method returns an ArrayList containing all nodes' data in order without modifying the list.
     * @time-complexity O(n) - The method traverses the entire list to collect all the nodes' data, where n is the number of nodes.
     */
    public ArrayList<String> getAllNodes() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < this.countNodes; i++) {
            Object data = this.getObjectAt(i);
            if (data == null) {
                list.add(null);  // Explicitly add null if data is null
            } else {
                list.add(data.toString());
            }
        }
        return list;
    }

    /**
     * Converts the entire linked list to a human-readable string format where each node's data is displayed.
     * The data is shown in the format: [data1 -> data2 -> ... -> dataN].
     *
     * @return A string representation of the list, where each node's data is connected by " -> ".
     * The string begins with "[" and ends with "]".
     * @pre-condition The list can be empty or contain any number of nodes. If the list is empty, the string will return "[]".
     * @post-condition The original linked list remains unmodified. A string representation of the list is returned.
     * @time-complexity O(n) - The method iterates through the entire list to build the string, where n is the number of nodes in the list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        ObjectNode temp = head;

        while (temp != null) {
            result.append(temp.getData());
            if (temp.getLink() != null) {
                result.append(" -> ");
            }
            temp = temp.getLink();
        }

        result.append("]");
        return result.toString();
    }

}
