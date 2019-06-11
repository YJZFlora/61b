package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    // test methods
    @Test
    public void test1() {
        // add & get smallest
        ArrayHeapMinPQ<String> t = new ArrayHeapMinPQ<>();
        for (int i = 400; i > 0; i--) {
            t.add("hi" + i, i);
            String smallest = t.getSmallest();
            assertEquals("hi" + i, smallest);
        }
    }

    @Test
    public void test2() {
        // contains
        ArrayHeapMinPQ<String> t = new ArrayHeapMinPQ<>();
        for (int i = 1; i < 400; i++) {  // add 1 ~ 400
            t.add("hi" + i, i);
            assertTrue(t.contains("hi" + i));
        }

        int n = t.size();

        // remove smallest
        for (int i = 1; i < 381; i++) { // remove 1 ~ 30
            String removed = t.removeSmallest();
            assertEquals("hi" + i, removed);
            assertFalse(t.contains(removed));
        }
    }

    @Test
    public void test3() {
        // change priority
        ArrayHeapMinPQ<String> t = new ArrayHeapMinPQ<>();

        for (int i = 1; i < 500; i++) {  // add 1 ~ 400
            t.add("hi" + (500 - i), i);
        }

        int n = t.size();  //399

        for (int i = 1; i < 500; i++) {
            t.changePriority("hi" + i, i);
        }

        for (int i = 1; i < 500; i++) {
            String removed = t.removeSmallest();
            assertEquals("hi" + i, removed);
        }

    }

    @Test
    public void compareTest() {
        NaiveMinPQ<String> expected = new NaiveMinPQ<>();
        ArrayHeapMinPQ<String> actual = new ArrayHeapMinPQ<>();

        int begin = 0;
        for (int i = 1; i < 1000; i++) {
            int p = StdRandom.uniform(begin, begin + 50);
            String s = "st" + p;
            expected.add(s, p);
            actual.add(s, p);
            begin = begin + 50;
        }
        for (int j = 0; j < 200; j++) {
            assertEquals(expected.removeSmallest(), actual.removeSmallest());
            assertEquals(expected.size(), actual.size());
        }

        // change priority
        for (int i = 1; i < 500; i++) {  // add 1 ~ 400
            expected.add("hi" + (500 - i), i);
            actual.add("hi"+ (500 - i), i);
        }
        for (int i = 1; i < 500; i++) {
            expected.changePriority("hi" + i, i);
            actual.changePriority("hi" + i, i);
            assertEquals(expected.getSmallest(), actual.getSmallest());

        }

        for (int i = 1; i < 500; i++) {
            assertEquals(expected.removeSmallest(), actual.removeSmallest());
        }

    }

    // test runtime
    @Test
    public void testRuntime() {
        NaiveMinPQ<String> naive = new NaiveMinPQ<>();
        ArrayHeapMinPQ<String> arrayheap = new ArrayHeapMinPQ<>();

        //navie time

        Stopwatch sw = new Stopwatch();
        int begin = 0;
        for (int i = 1; i < 30000; i++) {
            int p = StdRandom.uniform(begin, begin + 50);
            String s = "st" + p;
            naive.add(s, p);

            begin = begin + 50;

            int np = StdRandom.uniform(begin, begin + 50);
            naive.changePriority(s, np);

            begin = begin + 50;

        }
        System.out.println("NavieMinPQ: " + sw.elapsedTime() +  " seconds.");

        //my time
        Stopwatch sw2 = new Stopwatch();
        int begin2 = 0;
        for (int i = 1; i < 30000; i++) {
            int p = StdRandom.uniform(begin2, begin2 + 50);
            String s2 = "st" + p;
            arrayheap.add(s2, p);

            begin2 = begin2 + 50;

            int np2 = StdRandom.uniform(begin2, begin2 + 50);
            arrayheap.changePriority(s2, np2);

            begin2 = begin2 + 50;

        }
        System.out.println("ArrayHeapMinPQ: " + sw2.elapsedTime() +  " seconds.");

    }
}
