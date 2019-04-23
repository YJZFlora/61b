import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    String log = "\n";
    @Test
    public void test1() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        int testTime = StdRandom.uniform(10, 50);

        for (int i = 0; i < testTime; i++) {
            int randomInt = StdRandom.uniform(100);
            sad.addFirst(randomInt);
            sol.addFirst(randomInt);
            log = log + "addFirst(" + randomInt +")\n";
        }

        // test removeLast()
        for (int i=0; i< testTime; i++) {
            Integer actual = sad.removeLast();
            Integer expected = sol.removeLast();
            log = log + "removeLast()\n";
            assertEquals(log, expected, actual);
        }

        for (int i = 0; i < testTime; i++) {
            int randomInt = StdRandom.uniform(100);
            sad.addLast(randomInt);
            sol.addLast(randomInt);
            log = log + "addLast(" + randomInt +")\n";
        }

        // test removeFirst
        for (int i=0; i< testTime; i++) {
            Integer actual = sad.removeFirst();
            Integer expected = sol.removeFirst();
            log = log + "removeFirst()\n";
            assertEquals(log, expected, actual);
        }
    }

}
