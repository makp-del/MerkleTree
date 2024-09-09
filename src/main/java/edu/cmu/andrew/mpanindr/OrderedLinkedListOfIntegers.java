//Andrew ID: mpanindr
//Name: Manjunath K P

package edu.cmu.andrew.mpanindr;

import edu.colorado.nodes.ObjectNode;

import java.util.Random;

/**
 * The `OrderedLinkedListOfIntegers` class implements a singly linked list of integers, where the elements are stored
 * in sorted order (non-decreasing). This class supports operations like adding integers in sorted order, merging two
 * ordered lists, and iterating through the list.
 * <p>
 * Key Features:
 * - Add integers in sorted order, ensuring that the list maintains the order after each insertion.
 * - Iterate through the list using methods like `reset()`, `hasNext()`, and `next()`.
 * - Merge two ordered linked lists into a single sorted list.
 * - Provides a string representation of the list through the `toString()` method, showing the elements in order.
 * <p>
 * Pre-condition:
 * - The input for `sortedAdd()` must be a valid integer, and the input lists for `merge()` must be ordered in
 * non-decreasing order. The linked list can be empty or contain one or more elements.
 * <p>
 * Post-condition:
 * - After adding an integer, the list remains sorted in non-decreasing order.
 * - The merge method returns a new ordered list that contains all elements from both input lists, without modifying
 * the original lists.
 * - The iterator methods (`reset()`, `hasNext()`, `next()`) allow traversal through the list, advancing one node at
 * a time.
 * <p>
 * Time Complexity:
 * - Adding an element (`sortedAdd()`) takes O(n), where n is the number of elements in the list, since the method may
 * need to traverse the list to find the insertion point.
 * - Merging two lists (`merge()`) takes O(n + m), where n and m are the sizes of the two input lists, as the method
 * needs to traverse both lists once.
 * - Iteration methods (`reset()`, `hasNext()`, `next()`) all take O(1) time, as they only involve moving the iterator
 * or accessing the current node.
 * - The `toString()` method has O(n) complexity, where n is the number of elements in the list, since it needs to
 * traverse the list to build the string.
 * <p>
 * Example Usage:
 * - You can create an ordered linked list and add integers in sorted order.
 * - You can merge two ordered lists and iterate through the merged list using the iterator methods.
 * - The list is displayed in a human-readable format using the `toString()` method.
 * <p>
 * Author: Manjunath K P
 * Andrew ID: mpanindr
 */
public class OrderedLinkedListOfIntegers {
    private ObjectNode head;
    private ObjectNode iterator;

    /**
     * Constructor to initialize an empty ordered linked list.
     *
     * @pre-condition No pre-conditions. This constructor can be called at any time to create an empty list.
     * @post-condition Initializes an empty linked list where both head and iterator are set to null.
     * @time-complexity O(1) - Constant time initialization of the list.
     */
    public OrderedLinkedListOfIntegers() {
        head = null;
        iterator = null;
    }

    /**
     * Merges two ordered linked lists into a single ordered linked list.
     *
     * @param list1 - The first sorted list to be merged.
     * @param list2 - The second sorted list to be merged.
     * @return OrderedLinkedListOfIntegers - A new ordered linked list containing all elements from list1 and list2.
     * @pre-condition Both input lists (list1 and list2) must be sorted in non-decreasing order.
     * The input lists must not contain null elements.
     * @post-condition Returns a new `OrderedLinkedListOfIntegers` that contains all the elements from both input lists,
     * merged in non-decreasing order. The input lists themselves are not modified.
     * @time-complexity O(n + m) - Where n is the number of elements in list1 and m is the number of elements in list2.
     * The method iterates over both lists once to merge them.
     */
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

    /**
     * Adds an integer to the linked list in sorted order.
     *
     * @param value The integer to be added in sorted order.
     * @pre-condition The list can contain any number of elements, and the input value should be a valid integer.
     * @post-condition The integer will be inserted into the linked list while maintaining sorted order.
     * @time-complexity O(n) - The time complexity is linear since in the worst case, the method needs to traverse the entire list to find the correct insertion point.
     */
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

    /**
     * Resets the iterator to the start of the linked list.
     *
     * @pre-condition The list can contain any number of elements, and the iterator may be at any position.
     * @post-condition The iterator is reset to the head of the linked list, allowing iteration to start from the beginning.
     * @time-complexity O(1) - The reset operation is constant time.
     */
    public void reset() {
        iterator = head;
    }

    /**
     * Checks if there are more elements in the list during iteration.
     *
     * @return boolean - true if there are more elements, false otherwise.
     * @pre-condition The iterator must have been initialized, and reset() must be called before iteration begins.
     * @post-condition Returns true if there are more elements in the list to iterate through, false otherwise.
     * @time-complexity O(1) - Checking the next element in the list is constant time.
     */
    public boolean hasNext() {
        return iterator != null;
    }

    /**
     * Returns the next element in the list during iteration.
     *
     * @return Integer - The data of the next element in the list, or null if there are no more elements.
     * @pre-condition The iterator must have been initialized, and reset() must be called before iteration begins. hasNext() should be called before calling next() to avoid null pointer exceptions.
     * @post-condition The iterator advances to the next element in the list, and the current element's data is returned.
     * @time-complexity O(1) - Accessing the next element is constant time.
     */
    public Integer next() {
        if (iterator == null) {
            return null;
        }
        int data = (int) iterator.getData();
        iterator = iterator.getLink();
        return data;
    }

    /**
     * Converts the linked list into a string representation.
     *
     * @return String - A string representation of the linked list in the format "[element1 -> element2 -> ...]".
     * @pre-condition The list can be empty or contain one or more elements. No specific order is required.
     * @post-condition Returns a string that represents the linked list, where each element is separated by an arrow (" -> ").
     * If the list is empty, the result will be an empty list notation "[]".
     * @time-complexity O(n) - Where n is the number of nodes in the linked list. The method iterates through the entire list once.
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
