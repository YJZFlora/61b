import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("madam"));

        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("cat"));
    }

    @Test
    public void testoffByOnePalindrome() {
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("ab", offByOne));
        assertTrue(palindrome.isPalindrome("EHJKID", offByOne));
        assertTrue(palindrome.isPalindrome("FlakE", offByOne));
        assertTrue(palindrome.isPalindrome("flAke", offByOne));

        assertFalse(palindrome.isPalindrome("Flake"));

    }

    @Test
    public void testoffByNPalindrome() {
        CharacterComparator zero = new OffByN(0);
        assertTrue(palindrome.isPalindrome("madam", zero));
        assertTrue(palindrome.isPalindrome("noon", zero));
        assertTrue(palindrome.isPalindrome("a", zero));

        assertFalse(palindrome.isPalindrome("Aa", zero));
        assertFalse(palindrome.isPalindrome("cat", zero));

        CharacterComparator one = new OffByN(1);
        assertTrue(palindrome.isPalindrome("flake", one));
        assertTrue(palindrome.isPalindrome("MopN", one));
        assertTrue(palindrome.isPalindrome("a", one));
        assertTrue(palindrome.isPalindrome("%&", one));

        assertFalse(palindrome.isPalindrome("Ab", one));
        assertFalse(palindrome.isPalindrome("caD", one));



    }
}
