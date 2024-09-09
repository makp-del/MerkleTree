//Andrew ID: mpanindr
//Name: Manjunath K P

package edu.colorado.nodes;

/**
 * The ObjectNode class represents a node in a singly linked list structure.
 * Each node stores a piece of data (an Object) and a reference (link) to the next node in the list.
 * This class provides various methods to manipulate nodes within a linked list, such as adding nodes,
 * copying lists, and finding nodes based on position or value.
 * <p>
 * Key Features:
 * - Ability to create and link nodes, forming a singly linked list.
 * - Support for adding nodes, removing nodes, and accessing data and links.
 * - Utility methods for copying lists, both iteratively and recursively.
 * - Support for computing the length of a list using both iterative and recursive approaches.
 * - Additional methods for extracting parts of a list, displaying every third element,
 * and searching for nodes by their data.
 * <p>
 * Example Usage:
 * - Create a node with data and link it to other nodes.
 * - Traverse the list starting from the head node, accessing data or applying operations to nodes.
 * - Copy an existing linked list into a new list.
 * <p>
 * Pre-condition:
 * - The data for each node may be null, and the link may also be null (indicating the end of the list).
 * <p>
 * Post-condition:
 * - A singly linked list is formed with each node correctly referencing the next node in sequence.
 * - Methods operate on the linked list to modify, traverse, or manipulate its nodes.
 * <p>
 * Time Complexity:
 * - Most methods operate in O(1) time for individual node operations (e.g., setting data or link).
 * - Methods that traverse or copy the entire list have O(n) complexity, where n is the number of nodes.
 * <p>
 * Note:
 * - This class is designed for educational purposes to demonstrate fundamental linked list operations
 * and may not be optimized for production use.
 * - The class assumes that data stored in nodes can be compared using standard Java object comparison techniques.
 * <p>
 * Author: Manjunath K P
 * Andrew ID: mpanindr
 */
public class ObjectNode {
    private Object data;
    private ObjectNode link;


    /**
     * Constructs a new ObjectNode with specified data and a link to the next node.
     *
     * @param initialData The data that this node will store. It can be any object.
     * @param initialLink A reference to the next node in the linked list. This can be null if there is no next node.
     * @pre-condition No specific pre-condition. The data and link can be null.
     * @post-condition A new node is created with the provided data and link to the next node.
     * If the link is null, this node will be the last in the list.
     * @time-complexity O(1) - The constructor only assigns values to the instance variables.
     */
    public ObjectNode(Object initialData, ObjectNode initialLink) {
        data = initialData;
        link = initialLink;
    }

    /**
     * Creates a deep copy of a singly linked list starting from the given source node.
     *
     * @param source The head node of the list to be copied. It can be null, indicating an empty list.
     * @return The head node of the newly created list, which is a deep copy of the original list.
     * Returns null if the source list is empty (i.e., source is null).
     * @pre-condition The source node should be either null (indicating an empty list) or a valid reference to the head of a list.
     * @post-condition A new linked list is created and each node from the source list is copied into the new list.
     * The returned list will have the same sequence of data as the source list, but will have new nodes.
     * @time-complexity O(n) - The method iterates over each node of the source list exactly once, where n is the number of nodes in the list.
     */
    public static ObjectNode listCopy(ObjectNode source) {
        ObjectNode copyHead;
        ObjectNode copyTail;

        // Handle the special case of the empty list.
        if (source == null) {
            return null;
        }

        // Make the first node for the newly created list.
        copyHead = new ObjectNode(source.data, null);
        copyTail = copyHead;

        // Make the rest of the nodes for the newly created list.
        while (source.link != null) {
            source = source.link;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.link;
        }

        // Return the head reference for the new list.
        return copyHead;
    }

    /**
     * Recursively creates a deep copy of a linked list starting from the given node.
     * Each node in the source list is copied to a new list with identical data and structure.
     *
     * @param source The head node of the linked list to be copied. This can be null, indicating an empty list.
     * @return The head node of the newly copied linked list. If the source list is empty (null), it returns null.
     * @pre-condition The source node must be either null or a valid node within a linked list structure.
     * @post-condition A new linked list is created, which is a deep copy of the source list.
     * The original list remains unchanged.
     * @time-complexity O(n) - The method traverses through the entire list once, where n is the number of nodes in the source list.
     * Each recursive call processes one node.
     */
    public static ObjectNode listCopyRec(ObjectNode source) {
        if (source == null) {
            return null;
        }

        ObjectNode newNode = new ObjectNode(source.getData(), null);
        newNode.setLink(listCopyRec(source.getLink()));

        return newNode;
    }

    /**
     * Copy a list, returning both a head and tail reference for the copy.
     *
     * @param source the head of a linked list that will be copied (which may be
     *               an empty list in where source is null)
     * @return The method has made a copy of the linked list starting at
     * source.  The return value is an
     * array where the [0] element is a head reference for the copy and the [1]
     * element is a tail reference for the copy.
     * @throws OutOfMemoryError Indicates that there is insufficient memory for the new list.
     **/
    public static ObjectNode[] listCopyWithTail(ObjectNode source) {
        ObjectNode copyHead;
        ObjectNode copyTail;
        ObjectNode[] answer = new ObjectNode[2];

        // Handle the special case of the empty list.
        if (source == null)
            return answer; // The answer has two null references .

        // Make the first node for the newly created list.
        copyHead = new ObjectNode(source.data, null);
        copyTail = copyHead;

        // Make the rest of the nodes for the newly created list.
        while (source.link != null) {
            source = source.link;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.link;
        }

        // Return the head and tail references.
        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }

    /**
     * Computes the length of the linked list iteratively, starting from the given head node.
     *
     * @param head The head node of the list for which the length is being computed.
     * @return The number of nodes in the list.
     * @pre-condition The head node may be null (indicating an empty list) or a valid reference to the head of the list.
     * @post-condition The number of nodes in the list is calculated and returned.
     * @time-complexity O(n) - The method traverses each node in the list exactly once, where n is the number of nodes in the list.
     */
    public static int listLength(ObjectNode head) {
        ObjectNode cursor;
        int answer = 0;

        for (cursor = head; cursor != null; cursor = cursor.link) {
            answer++;
        }

        return answer;
    }

    /**
     * Recursively computes the length of the linked list, starting from the given head node.
     *
     * @param head The head node of the list for which the length is being computed.
     * @return The number of nodes in the list.
     * @pre-condition The head node may be null (indicating an empty list) or a valid reference to the head of the list.
     * @post-condition The number of nodes in the list is calculated and returned recursively.
     * @time-complexity O(n) - The method makes a recursive call for each node, where n is the number of nodes in the list.
     */
    public static int listLengthRec(ObjectNode head) {
        if (head == null) {
            return 0;
        }
        return 1 + listLengthRec(head.link);
    }

    /**
     * Copies a part of a linked list from the specified start node to the end node (inclusive).
     * The result is a new list that includes all nodes from the start to the end.
     *
     * @param start The node from where the copy operation begins. This must be a non-null reference.
     * @param end   The node where the copy operation ends (inclusive). This must be a non-null reference.
     * @return An array of two ObjectNode references where:
     * - answer[0] is the head of the new copied list.
     * - answer[1] is the tail of the new copied list.
     * @throws NullPointerException     If the start node is null.
     * @throws IllegalArgumentException If the end node is not found in the list after the start node.
     * @pre-condition The start and end nodes must be non-null and part of the same list.
     * The start node must precede or be equal to the end node in the list.
     * @post-condition A new list is created, containing all nodes from the start node to the end node (inclusive).
     * Both head and tail of the new list are returned in an array.
     * @time-complexity O(n) - The method traverses the nodes between start and end, where n is the number of nodes between them.
     */
    public static ObjectNode[] listPart(ObjectNode start, ObjectNode end) {
        ObjectNode copyHead;
        ObjectNode copyTail;
        ObjectNode cursor;
        ObjectNode[] answer = new ObjectNode[2];

        // Make the first node for the newly created list. Notice that this will
        // cause a NullPointerException if start is null.
        copyHead = new ObjectNode(start.data, null);
        copyTail = copyHead;
        cursor = start;

        // Make the rest of the nodes for the newly created list.
        while (cursor != end) {
            cursor = cursor.link;
            if (cursor == null) {
                throw new IllegalArgumentException("end node was not found on the list");
            }
            copyTail.addNodeAfter(cursor.data);
            copyTail = copyTail.link;
        }

        // Return the head and tail references
        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }

    /**
     * Finds and returns the node at a specified position in the linked list.
     * The position is 1-based, meaning the head node is at position 1.
     *
     * @param head     The head of the linked list. Can be null if the list is empty.
     * @param position The 1-based position of the node to retrieve.
     * @return The node at the specified position, or null if the position is beyond the length of the list.
     * @throws IllegalArgumentException If the position is less than or equal to 0.
     * @pre-condition The position must be a positive integer greater than 0.
     * @post-condition Returns the node at the given position or null if the list is shorter than the requested position.
     * @time-complexity O(n) - The method traverses the list from the head to the specified position, where n is the position.
     */
    public static ObjectNode listPosition(ObjectNode head, int position) {
        ObjectNode cursor;
        int i;

        if (position <= 0) {
            throw new IllegalArgumentException("position is not positive");
        }

        cursor = head;
        for (i = 1; (i < position) && (cursor != null); i++) {
            cursor = cursor.link;
        }

        return cursor;
    }

    /**
     * Searches for a specific target data in the linked list and returns the first node that contains it.
     *
     * @param head   The head of the linked list. Can be null if the list is empty.
     * @param target The data to search for in the linked list.
     * @return The first node that contains the target data, or null if the target is not found.
     * @pre-condition The linked list can be empty (head is null). The target should be a valid data type comparable to the node data.
     * @post-condition Returns the first node containing the target data or null if the target is not found.
     * @time-complexity O(n) - The method traverses the list from the head, where n is the number of nodes in the list.
     */
    public static ObjectNode listSearch(ObjectNode head, Object target) {
        ObjectNode cursor;

        for (cursor = head; cursor != null; cursor = cursor.link) {
            if (target.equals(cursor.data)) {
                return cursor;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        // Initialize the list with 'a' to 'z'
        ObjectNode letters = new ObjectNode('a', null);
        char start = 'b';  // Start from 'b' since 'a' is already added
        ObjectNode current = letters;

        for (int i = 1; i < 26; i++) {
            current.addNodeAfter(start);
            current = current.getLink();
            start++;
        }

        // Create copies of the list
        ObjectNode k = ObjectNode.listCopy(letters);
        ObjectNode k2 = ObjectNode.listCopyRec(letters);

        // Display the original list
        System.out.println("The list is " + letters);
        System.out.println();

        // Display every third element
        letters.displayEveryThird();

        // Print the length of the original list using both methods
        System.out.println("Letters length with listLength: " + ObjectNode.listLength(letters));
        System.out.println("Letters length with listLengthRec: " + ObjectNode.listLengthRec(letters));

        // Display and check the length of the copied lists
        System.out.println("k: " + k);
        System.out.println("k length with listLength: " + ObjectNode.listLength(k));
        System.out.println("k length with listLengthRec: " + ObjectNode.listLengthRec(k));

        System.out.println("k2: " + k2);
        System.out.println("k2 length with listLength: " + ObjectNode.listLength(k2));
        System.out.println("k2 length with listLengthRec: " + ObjectNode.listLengthRec(k2));
    }

    /**
     * Adds a new node with the specified data after this node.
     *
     * @param item The data for the new node to be added after this node.
     * @throws OutOfMemoryError If there is insufficient memory to allocate a new node.
     * @pre-condition This node should not be the tail of the list. The item to be added must not be null.
     * @post-condition A new node containing the given item is added after this node.
     * The link to the next node is updated accordingly.
     * @time-complexity O(1) - The method creates a new node and updates the link.
     */
    public void addNodeAfter(Object item) {
        link = new ObjectNode(item, link);
    }

    /**
     * Retrieves the data stored in this node.
     *
     * @return The data stored in this node.
     * @pre-condition The node must have been initialized. The data may be null if it was set as such.
     * @post-condition The data of this node is returned.
     * @time-complexity O(1) - The method simply returns the value of the data field.
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the data in this node.
     *
     * @param newData The new data to be stored in this node.
     * @pre-condition None.
     * @post-condition The data in this node has been replaced with the provided new data.
     * @time-complexity O(1) - This is a simple assignment operation that occurs in constant time.
     */
    public void setData(Object newData) {
        data = newData;
    }

    /**
     * Retrieves the reference to the next node linked to this node.
     *
     * @return A reference to the node following this node in the linked list.
     * Returns null if there is no node after this one.
     * @pre-condition The node must have been initialized. The link may be null if this is the last node.
     * @post-condition The link to the next node is returned. If no node follows, null is returned.
     * @time-complexity O(1) - The method simply returns the value of the link field.
     */
    public ObjectNode getLink() {
        return link;
    }

    /**
     * Sets the link to the next node after this node.
     *
     * @param newLink A reference to the node that should appear after this node in the linked list.
     * @pre-condition None.
     * @post-condition The link to the next node has been updated to point to the provided new node.
     * @time-complexity O(1) - This is a simple reference update, which takes constant time.
     */
    public void setLink(ObjectNode newLink) {
        link = newLink;
    }

    /**
     * Displays every third node's data in the list.
     *
     * @pre-condition The current object is a valid list starting from the current node.
     * @post-condition Every third node's data is printed to the console.
     * @time-complexity O(n) - The method traverses the entire list once, where n is the number of nodes in the list.
     */
    public void displayEveryThird() {
        ObjectNode temp = this;
        int n = 1;

        while (temp != null) {
            if (n % 3 == 0) {
                System.out.println("The value is " + temp.data);
            }
            temp = temp.link;
            n++;
        }
    }

    /**
     * Removes the node immediately following this node in the linked list.
     *
     * @pre-condition This node must not be the last node in the list (i.e., the link field must not be null).
     * @post-condition The node following this node has been removed. If there are more nodes after the removed node, they are still
     * linked in the list.
     * @time-complexity O(1) - The operation only involves updating the link of this node, making it constant time.
     */
    public void removeNodeAfter() {
        link = link.link;
    }

    /**
     * Returns a string representation of the linked list starting from this node.
     *
     * @return A string representation of the list, where each node's data is separated by " -> ".
     * @pre-condition None.
     * @post-condition Returns a string representation of the nodes in the list, where each node's data is
     * separated by " -> ".
     * @time-complexity O(n) - Where n is the number of nodes in the list, as it must traverse the entire list to generate the string.
     */
    @Override
    public String toString() {
        String temp = "";
        ObjectNode current = this;
        while (current != null) {
            temp += current.data;
            if (current.link != null) {
                temp += " -> ";
            }
            current = current.link;
        }
        return temp;
    }

}
           