package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(6);
        assertTrue(arb.isEmpty());
        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        int actual = arb.peek();
        assertEquals(0, actual);
        int a2 = arb.dequeue();
        assertEquals(0, a2);
        int a3 = arb.peek();
        assertEquals(1, a3);

        assertFalse(arb.isFull());
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        assertTrue(arb.isFull());


    }
}
