package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void testNavie() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        double retx = ret.getX(); // evaluates to 3.3
        assertEquals(3.3, retx, 0.0f);
        double rety = ret.getY(); // evaluates to 4.4
        assertEquals(4.4, rety, 0.0f);
    }

    @Test
    public void testNearest() {
        List<Point> testList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Random rand = new Random();
            Point newP = new Point(rand.nextDouble(), rand.nextDouble());
            testList.add(newP);
        }

        NaivePointSet np = new NaivePointSet(testList);
        KDTree kd = new KDTree(testList);

        for (int i = 0; i < 10000; i++) {
            Random rand = new Random();
            double d1 = rand.nextDouble();
            double d2 = rand.nextDouble();

            assertEquals(np.nearest(d1, d2).getX(), kd.nearest(d1, d2).getX(), 0.0f);

            assertEquals(np.nearest(d1, d2).getY(), kd.nearest(d1, d2).getY(), 0.0f);

        }
    }

    public static void main(String[] args) {
        List<Point> testList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Random rand = new Random();
            Point newP = new Point(rand.nextDouble(), rand.nextDouble());
            testList.add(newP);
        }

        // Naive
        Stopwatch swNaive = new Stopwatch();

        NaivePointSet np = new NaivePointSet(testList);
        for (int i = 0; i < 10000; i += 1) {
            Random rand = new Random();
            double d1 = rand.nextDouble();
            double d2 = rand.nextDouble();
            np.nearest(d1, d2);
        }
        System.out.println("Naive time elapsed: " + swNaive.elapsedTime() +  " seconds.");

        // KDTree
        Stopwatch swKDT = new Stopwatch();

        KDTree kd = new KDTree(testList);
        for (int i = 0; i < 10000; i += 1) {
            Random rand = new Random();
            double d1 = rand.nextDouble();
            double d2 = rand.nextDouble();
            kd.nearest(d1, d2);
        }
        System.out.println("KDTree time elapsed: " + swKDT.elapsedTime() +  " seconds.");



    }

}
