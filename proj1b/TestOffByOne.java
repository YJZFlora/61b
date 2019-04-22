import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testequalChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertTrue(offByOne.equalChars('r', 'q'));
        assertTrue(offByOne.equalChars('@', 'A'));
        assertTrue(offByOne.equalChars('A', 'B'));

        assertFalse(offByOne.equalChars('a','a'));
        assertFalse(offByOne.equalChars('a','z'));
        assertFalse(offByOne.equalChars('a','B'));
    }

}