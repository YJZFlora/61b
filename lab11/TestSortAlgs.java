import edu.princeton.cs.algs4.Queue;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> tas = new Queue<>();
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        tas = QuickSort.quickSort(tas);
        assertTrue(isSorted(tas));

        Queue<Integer> t2 = new Queue<>();
        t2.enqueue(5);
        t2.enqueue(3);
        t2.enqueue(2);
        t2.enqueue(1);
        t2.enqueue(7);
        t2.enqueue(8);
        t2.enqueue(4);
        t2.enqueue(6);
        t2 = QuickSort.quickSort(t2);
        assertTrue(isSorted(t2));

        Queue<Integer> t3 = new Queue<>();
        for(int i = 0; i < 10000; i++) {
            int r = StdRandom.uniform(10000);
            t3.enqueue(r);
        }
        t3 = QuickSort.quickSort(t3);
        assertTrue(isSorted(t3));
    }

    @Test
    public void testMergeSort() {

        Queue<Integer> t2 = new Queue<>();
        t2.enqueue(5);
        t2.enqueue(3);
        t2.enqueue(2);
        t2.enqueue(1);
        t2.enqueue(7);
        t2.enqueue(8);
        t2.enqueue(4);
        t2.enqueue(6);
        t2 = MergeSort.mergeSort(t2);
        assertTrue(isSorted(t2));


        Queue<Integer> t3 = new Queue<>();
        for(int i = 0; i < 10000; i++) {
            int r = StdRandom.uniform(10000);
            t3.enqueue(r);
        }
        t3 = MergeSort.mergeSort(t3);
        assertTrue(isSorted(t3));


    }

/*
    @Test
    public void testmakeSingleItemQueues() {
        Queue<String> tas = new Queue<>();
        tas.enqueue("a");
        tas.enqueue("b");
        tas.enqueue("c");
        Queue<Queue<String>> rQ = MergeSort.makeSingleItemQueues(tas);
    }



    @Test
    public void testmergeSortedQueues() {
        Queue<Integer> q1 = new Queue<>();
        q1.enqueue(1);
        q1.enqueue(3);
        q1.enqueue(5);
        Queue<Integer> q2 = new Queue<>();
        q2.enqueue(2);
        q2.enqueue(4);
        q2.enqueue(6);
        q2.enqueue(8);

        Queue<Integer> q4 = MergeSort.mergeSortedQueues(q1,q2);

        Queue<Integer> q3 = new Queue<>();
        q3.enqueue(1);
        q3.enqueue(2);
        q3.enqueue(3);
        q3.enqueue(4);
        q3.enqueue(5);
        q3.enqueue(6);
        q3.enqueue(8);

        assertSame(q3, q4);
    }
*/

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
