import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testisSameNumber() {
        Integer a = 128;
        Integer b = 12;
        Integer c = 128;
        assertTrue(Flik.isSameNumber(a, c));
        assertFalse("cannot detect that numbers are not equal", Flik.isSameNumber(a, b));
    }
}



