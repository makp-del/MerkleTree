package edu.cmu.andrew.mpanindr;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    // Test adding a node at the end of the list
    @Test
    void testAddAtEndNode() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode('a');
        assertEquals('a', list.getLast());
    }

    // Test adding a node at the front of the list
    @Test
    void testAddAtFrontNode() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtFrontNode('b');
        list.addAtFrontNode('a');
        assertEquals('a', list.getObjectAt(0));
        assertEquals('b', list.getObjectAt(1));
    }

    // Test counting the number of nodes in the list
    @Test
    void testCountNodes() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode('a');
        list.addAtEndNode('b');
        assertEquals(2, list.countNodes());
    }

    // Test retrieving the last element in the list
    @Test
    void testGetLast() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode('a');
        list.addAtEndNode('b');
        assertEquals('b', list.getLast());
    }

    // Test retrieving an object by index
    @Test
    void testGetObjectAt() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode('a');
        list.addAtEndNode('b');
        list.addAtEndNode('c');
        assertEquals('a', list.getObjectAt(0));
        assertEquals('b', list.getObjectAt(1));
        assertEquals('c', list.getObjectAt(2));
    }

    // Test iterator functionality: hasNext() and next()
    @Test
    void testIterator() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode('a');
        list.addAtEndNode('b');
        list.addAtEndNode('c');

        list.reset(); // Reset the iterator
        assertTrue(list.hasNext());
        assertEquals('a', list.next());
        assertEquals('b', list.next());
        assertEquals('c', list.next());
        assertFalse(list.hasNext());
    }

    // Test iterator functionality on an empty list
    @Test
    void testIteratorEmptyList() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.reset(); // Reset the iterator
        assertFalse(list.hasNext());
        assertNull(list.next());
    }

    // Test retrieving an object at an index out of bounds
    @Test
    void testGetObjectAtOutOfBounds() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode('a');
        list.addAtEndNode('b');
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.getObjectAt(3); // Invalid index
        });
    }

    // Test duplicating the last node
    @Test
    void testDuplicateLastNode() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode('a');
        list.addAtEndNode('b');
        list.duplicateLastNode(); // Should duplicate 'b'
        assertEquals(3, list.countNodes());
        assertEquals('b', list.getLast());
    }

    // Test retrieving all nodes as an ArrayList
    @Test
    void testGetAllNodes() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode('a');
        list.addAtEndNode('b');
        list.addAtEndNode('c');
        assertEquals(3, list.getAllNodes().size());
        assertEquals("a", list.getAllNodes().get(0));
        assertEquals("b", list.getAllNodes().get(1));
        assertEquals("c", list.getAllNodes().get(2));
    }

    // Test toString method
    @Test
    void testToString() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode('a');
        list.addAtEndNode('b');
        list.addAtEndNode('c');
        assertEquals("[a -> b -> c]", list.toString());
    }

    // Test adding null values
    @Test
    void testAddNull() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode(null);
        assertEquals(1, list.countNodes());
        assertNull(list.getObjectAt(0));
    }

    // Test iterator reset functionality
    @Test
    void testIteratorReset() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addAtEndNode('a');
        list.addAtEndNode('b');
        list.reset();
        assertEquals('a', list.next());
        assertEquals('b', list.next());
        list.reset(); // Reset the iterator again
        assertEquals('a', list.next());
    }

    // Test the list when it's empty
    @Test
    void testEmptyList() {
        SinglyLinkedList list = new SinglyLinkedList();
        assertEquals(0, list.countNodes());
        assertNull(list.getLast());
        assertNull(list.next());
        assertFalse(list.hasNext());
    }
}