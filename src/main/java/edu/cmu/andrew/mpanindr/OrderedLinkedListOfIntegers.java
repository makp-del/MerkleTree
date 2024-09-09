package edu.cmu.andrew.mpanindr;

import edu.colorado.nodes.ObjectNode;

import java.util.Random;

public class OrderedLinkedListOfIntegers {
    private ObjectNode head;
    private ObjectNode iterator;

    // Constructor
    public OrderedLinkedListOfIntegers() {
        head = null;
        iterator = null;
    }

    // Method to add integers in a sorted order
    public void sortedAdd(int value) {
        ObjectNode newNode = new ObjectNode(value, null);

        if (head == null || (int) head.getData() >= value) {
            // Insert at the beginning if the list is empty or the value is less than the head
            newNode.setLink(head);
            head = newNode;
        } else {
            // Traverse the list to find the correct insertion point
            ObjectNode current = head;
            while (current.getLink() != null && (int) current.getLink().getData() < value) {
                current = current.getLink();
            }
            newNode.setLink(current.getLink());
            current.setLink(newNode);
        }
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
    public Integer next() {
        if (iterator == null) {
            return null;
        }
        int data = (int) iterator.getData();
        iterator = iterator.getLink();
        return data;
    }

    // Static method to merge two ordered lists
    public static OrderedLinkedListOfIntegers merge(OrderedLinkedListOfIntegers list1, OrderedLinkedListOfIntegers list2) {
        OrderedLinkedListOfIntegers mergedList = new OrderedLinkedListOfIntegers();
        ObjectNode current1 = list1.head;
        ObjectNode current2 = list2.head;

        // Traverse both lists and add elements in sorted order
        while (current1 != null && current2 != null) {
            if ((int) current1.getData() <= (int) current2.getData()) {
                mergedList.sortedAdd((int) current1.getData());
                current1 = current1.getLink();
            } else {
                mergedList.sortedAdd((int) current2.getData());
                current2 = current2.getLink();
            }
        }

        // Add remaining elements from list1
        while (current1 != null) {
            mergedList.sortedAdd((int) current1.getData());
            current1 = current1.getLink();
        }

        // Add remaining elements from list2
        while (current2 != null) {
            mergedList.sortedAdd((int) current2.getData());
            current2 = current2.getLink();
        }

        return mergedList;
    }

    // toString method to display the list as a string
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

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        OrderedLinkedListOfIntegers list1 = new OrderedLinkedListOfIntegers();
        OrderedLinkedListOfIntegers list2 = new OrderedLinkedListOfIntegers();

        Random rand = new Random();

        // Adding 20 random values to list1 and list2
        for (int i = 0; i < 20; i++) {
            list1.sortedAdd(rand.nextInt(100)); // Random values between 0 and 99
            list2.sortedAdd(rand.nextInt(100));
        }

        // Display the lists
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);

        // Merge the lists
        OrderedLinkedListOfIntegers mergedList = OrderedLinkedListOfIntegers.merge(list1, list2);

        // Display the merged list
        System.out.println("Merged List: " + mergedList);

        // Test iteration over the merged list
        System.out.println("Iterating over the merged list:");
        mergedList.reset();
        while (mergedList.hasNext()) {
            System.out.println(mergedList.next());
        }
    }
}
