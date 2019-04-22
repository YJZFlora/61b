import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator zero = new OffByN(0);
    static CharacterComparator one = new OffByN(1);
    static CharacterComparator three = new OffByN(3);

    @Test
    public void testequalChars() {
        assertTrue(zero.equalChars('a', 'a'));
        assertTrue(one.equalChars('a', 'b'));
        assertTrue(three.equalChars('a', 'd'));
    }

}
