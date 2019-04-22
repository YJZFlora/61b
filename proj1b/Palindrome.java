public class Palindrome extends LinkedListDeque {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);

        if (d.isEmpty() || d.size() == 1) {
            return true;
        }

        Object head = d.removeFirst();
        Object last = d.removeLast();
        String d2 = dequeToWord(d);

        while (head.equals(last)) {
            return isPalindrome(d2);
        }
        return false;
    }

    private String dequeToWord(Deque d) {
        String s = "";
        for (int i = 0; i < d.size(); i++) {
            s = s + String.valueOf(d.get(i));
        }
        return s;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        if (d.isEmpty() || d.size() == 1) {
            return true;
        }

        Object head = d.removeFirst();
        Object last = d.removeLast();
        String d2 = dequeToWord(d);

        while (cc.equalChars((char) head, (char) last)) {
            return isPalindrome(d2, cc);
        }

        return false;
    }

}
