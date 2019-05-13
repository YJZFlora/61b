import org.junit.Test;
import static org.junit.Assert.*;

public class UnionFindTest {
    @Test
    public void test1() {
          UnionFind one= new UnionFind(9);
          one.union(1, 3);
          one.union(2, 4);
          one.union(2, 5);
          one.union(3, 2);
          int four = one.find(1);

          assertEquals(4, four);
          assertEquals(4, one.parent(2));
        assertEquals(4, one.parent(5));
        assertEquals(5, one.sizeOf(5));



    }
}
