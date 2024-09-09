package edu.cmu.andrew.mpanindr;

import edu.colorado.nodes.ObjectNode;

import java.util.ArrayList;

public class SinglyLinkedList {
    private ObjectNode head;
    private ObjectNode tail;
    private int countNodes;
    private ObjectNode iterator;

    // Constructor
    public SinglyLinkedList() {
        head = null;
        tail = null;
        countNodes = 0;
        iterator = null;
    }

    // Method to add a node at the end of the list
    public void addAtEndNode(Object c) {
        ObjectNode newNode = new ObjectNode(c, null);

        if (head == null) { // If the list is empty, both head and tail point to the new node
            head = newNode;
            tail = newNode;
        } else {
            tail.setLink(newNode); // Link the new node after the tail
            tail = newNode; // Update the tail to the new node
        }

        countNodes++;
    }

    // Method to add a node at the front of the list
    public void addAtFrontNode(Object c) {
        ObjectNode newNode = new ObjectNode(c, head); // The new node points to the current head

        if (head == null) { // If the list is empty, set tail to the new node
            tail = newNode;
        }

        head = newNode; // Update the head to the new node
        countNodes++;
    }

    // Method to count the number of nodes in the list
    public int countNodes() {
        return countNodes;
    }

    // Method to get the data in the tail of the list
    public Object getLast() {
        if (tail != null) {
            return tail.getData();
        }
        return null; // Return null if the list is empty
    }

    // Method to get the object at a specific index (0-based)
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

    // Iterator reset method
    public void reset() {
        iterator = head;
    }

    // Iterator hasNext method
    public boolean hasNext() {
        return iterator != null;
    }

    // Iterator next method
    public Object next() {
        if (iterator == null) {
            return null;
        }

        Object data = iterator.getData();
        iterator = iterator.getLink();
        return data;
    }

    public void duplicateLastNode(){
        ObjectNode newNode = new ObjectNode(this.tail.getData(), null);
        this.tail.setLink(newNode);
        this.tail = newNode;
        this.countNodes++;
    }

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

    // toString method to return the list as a String
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

}
