package edu.colorado.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ObjectNodeTest {

    // Test the constructor and basic setup of an ObjectNode
    @Test
    void testConstructorAndGetters() {
        ObjectNode node = new ObjectNode('a', null);
        assertEquals('a', node.getData());
        assertNull(node.getLink());
    }

    // Test adding a node after the current node
    @Test
    void testAddNodeAfter() {
        ObjectNode node = new ObjectNode('a', null);
        node.addNodeAfter('b');
        assertEquals('b', node.getLink().getData());
        assertNull(node.getLink().getLink());
    }

    // Test removing a node after the current node
    @Test
    void testRemoveNodeAfter() {
        ObjectNode node = new ObjectNode('a', null);
        node.addNodeAfter('b');
        node.addNodeAfter('c');
        node.removeNodeAfter(); // Should remove 'b'
        assertEquals('b', node.getLink().getData());
    }

    // Test setting the data in a node
    @Test
    void testSetData() {
        ObjectNode node = new ObjectNode('a', null);
        node.setData('z');
        assertEquals('z', node.getData());
    }

    // Test setting the link in a node
    @Test
    void testSetLink() {
        ObjectNode node1 = new ObjectNode('a', null);
        ObjectNode node2 = new ObjectNode('b', null);
        node1.setLink(node2);
        assertEquals('b', node1.getLink().getData());
    }

    // Test copying a list
    @Test
    void testListCopy() {
        ObjectNode head = new ObjectNode('a', null);
        head.addNodeAfter('b');
        head.addNodeAfter('c');

        ObjectNode copy = ObjectNode.listCopy(head);
        assertEquals('a', copy.getData());
        assertEquals('c', copy.getLink().getData());
        assertEquals('b', copy.getLink().getLink().getData());

        // Ensure changes to the original list do not affect the copy
        head.setData('z');
        assertEquals('a', copy.getData()); // Should still be 'a'
    }

    // Test copying a list recursively
    @Test
    void testListCopyRec() {
        ObjectNode head = new ObjectNode('a', null);
        head.addNodeAfter('b');
        head.addNodeAfter('c');

        ObjectNode copy = ObjectNode.listCopyRec(head);
        assertEquals('a', copy.getData());
        assertEquals('c', copy.getLink().getData());
        assertEquals('b', copy.getLink().getLink().getData());

        // Ensure changes to the original list do not affect the copy
        head.setData('z');
        assertEquals('a', copy.getData()); // Should still be 'a'
    }

    // Test listCopyWithTail method
    @Test
    void testListCopyWithTail() {
        ObjectNode head = new ObjectNode('a', null);
        head.addNodeAfter('b');
        head.addNodeAfter('c');

        ObjectNode[] copyWithTail = ObjectNode.listCopyWithTail(head);
        ObjectNode copyHead = copyWithTail[0];
        ObjectNode copyTail = copyWithTail[1];

        assertEquals('a', copyHead.getData());
        assertEquals('b', copyTail.getData());
    }

    // Test listPart method with valid inputs
    @Test
    void testListPartValid() {
        ObjectNode head = new ObjectNode('a', null);
        ObjectNode mid = new ObjectNode('b', null);
        ObjectNode tail = new ObjectNode('c', null);

        head.setLink(mid);
        mid.setLink(tail);

        ObjectNode[] part = ObjectNode.listPart(head, tail);
        assertEquals('a', part[0].getData());
        assertEquals('c', part[1].getData());
    }

    // Test listPart method with invalid inputs (non-contiguous nodes)
    @Test
    void testListPartInvalid() {
        ObjectNode start = new ObjectNode('a', null);
        ObjectNode end = new ObjectNode('z', null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ObjectNode.listPart(start, end);
        });

        String expectedMessage = "end node was not found on the list";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test listSearch method to find a node
    @Test
    void testListSearch() {
        ObjectNode head = new ObjectNode('a', null);
        head.addNodeAfter('b');
        head.addNodeAfter('c');

        ObjectNode result = ObjectNode.listSearch(head, 'b');
        assertNotNull(result);
        assertEquals('b', result.getData());

        result = ObjectNode.listSearch(head, 'z');
        assertNull(result);
    }

    // Test listPosition method for valid positions
    @Test
    void testListPositionValid() {
        ObjectNode head = new ObjectNode('a', null);
        head.addNodeAfter('b');
        head.addNodeAfter('c');

        ObjectNode result = ObjectNode.listPosition(head, 1); // First node
        assertEquals('a', result.getData());

        result = ObjectNode.listPosition(head, 2); // Second node
        assertEquals('c', result.getData());
    }

    // Test listPosition method for invalid positions
    @Test
    void testListPositionInvalid() {
        ObjectNode head = new ObjectNode('a', null);
        head.addNodeAfter('b');
        head.addNodeAfter('c');

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ObjectNode.listPosition(head, 0);  // Invalid position
        });

        String expectedMessage = "position is not positive";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        assertNull(ObjectNode.listPosition(head, 10));  // Position out of bounds
    }

    // Test listLength method (iterative)
    @Test
    void testListLength() {
        ObjectNode head = new ObjectNode('a', null);
        head.addNodeAfter('b');
        head.addNodeAfter('c');

        assertEquals(3, ObjectNode.listLength(head));

        // Test empty list
        assertEquals(0, ObjectNode.listLength(null));
    }

    // Test listLengthRec method (recursive)
    @Test
    void testListLengthRec() {
        ObjectNode head = new ObjectNode('a', null);
        head.addNodeAfter('b');
        head.addNodeAfter('c');

        assertEquals(3, ObjectNode.listLengthRec(head));

        // Test empty list
        assertEquals(0, ObjectNode.listLengthRec(null));
    }

    // Test displaying every third element
    @Test
    void testDisplayEveryThird() {
        ObjectNode head = new ObjectNode('a', null);
        head.addNodeAfter('b');
        head.addNodeAfter('c');
        head.addNodeAfter('d');
        head.addNodeAfter('e');
        head.addNodeAfter('f');
        head.addNodeAfter('g');

        // Expecting: 'c', 'f'
        head.displayEveryThird();
        // This will print values, but for unit testing, you might want to redirect the console output.
        // Otherwise, this could be verified by manually observing the output.
    }

    // Test toString method
    @Test
    void testToString() {
        ObjectNode head = new ObjectNode('a', null);
        head.addNodeAfter('b');
        head.addNodeAfter('c');
        String expected = "a -> c -> b";
        assertEquals(expected, head.toString());
    }
}