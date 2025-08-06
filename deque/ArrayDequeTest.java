package deque;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic array deque tests. */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results. */
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();

        // A newly initialized deque should be empty.
        assertTrue("A newly initialized AD should be empty", ad1.isEmpty());

        ad1.addFirst("front");
        // The deque should now contain 1 item.
        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque(); // Expected output: front middle back
    }

    @Test
    /** Adds an item, then removes an item, and ensures that deque is empty afterwards. */
    public void addRemoveTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        // should be empty
        assertTrue("ad1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("ad1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("ad1 should be empty after removal", ad1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();   // Deque is now empty
        ad1.removeFirst();  // Should do nothing and return null
        ad1.removeLast();   // Should do nothing and return null

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {
        ArrayDeque<String>  ad1 = new ArrayDeque<>();
        ArrayDeque<Double>  ad2 = new ArrayDeque<>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();

        assertEquals("string", s);
        assertEquals(3.14159, d, 0.00001);
        assertTrue(b);
    }

    @Test
    /* check if null is returned when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        assertNull("Should return null when removeFirst is called on an empty Deque,", ad1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", ad1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct after resizing. */
    public void bigArrayDequeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        int N = 100000;
        for (int i = 0; i < N; i++) {
            ad1.addLast(i);
        }

        for (int i = 0; i < N / 2; i++) {
            assertEquals("Should have the same value", (Integer) i, ad1.removeFirst());
        }

        for (int i = N - 1; i >= N / 2; i--) {
            assertEquals("Should have the same value", (Integer) i, ad1.removeLast());
        }

        assertTrue("Deque should be empty after all removals", ad1.isEmpty());
    }

    @Test
    /** This test checks the get() method thoroughly.
     * It adds items, removes from front and back to cause pointer wrapping,
     * and then verifies that get() still retrieves the correct items.
     */
    public void getAfterWrapAroundTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        // Add 8 items, filling the initial array
        for (int i = 0; i < 8; i++) {
            ad.addLast(i); // Deque is [0, 1, 2, 3, 4, 5, 6, 7]
        }

        // Remove 4 from the front
        ad.removeFirst(); // [1, 2, 3, 4, 5, 6, 7]
        ad.removeFirst(); // [2, 3, 4, 5, 6, 7]
        ad.removeFirst(); // [3, 4, 5, 6, 7]
        ad.removeFirst(); // [4, 5, 6, 7] -> head should now be at physical index 4

        // Add 4 to the back, causing the tail to wrap around
        ad.addLast(8);  // [4, 5, 6, 7, 8]
        ad.addLast(9);  // [4, 5, 6, 7, 8, 9]
        ad.addLast(10); // [4, 5, 6, 7, 8, 9, 10]
        ad.addLast(11); // [4, 5, 6, 7, 8, 9, 10, 11] -> tail should wrap around to index 3

        // Now, check if get() can correctly retrieve all 8 logical items
        assertEquals("get(0) should be 4", (Integer) 4, ad.get(0));
        assertEquals("get(1) should be 5", (Integer) 5, ad.get(1));
        assertEquals("get(2) should be 6", (Integer) 6, ad.get(2));
        assertEquals("get(3) should be 7", (Integer) 7, ad.get(3));
        assertEquals("get(4) should be 8", (Integer) 8, ad.get(4));
        assertEquals("get(5) should be 9", (Integer) 9, ad.get(5));
        assertEquals("get(6) should be 10", (Integer) 10, ad.get(6));
        assertEquals("get(7) should be 11", (Integer) 11, ad.get(7));
        assertEquals("Size should be 8", 8, ad.size());
    }
}