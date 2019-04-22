public class LinkedListDeque<T> implements Deque<T> {

    private Node sentinel;
    private int size;

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        Node(T i) {
            item = i;
            prev = null;
            next = null;
        }
    }

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T i) {
        Node curr = new Node(i);
        curr.next = sentinel.next;
        sentinel.next.prev = curr;
        sentinel.next = curr;
        curr.prev = sentinel;
        size = size + 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addLast(T i) {
        Node curr = new Node(i);
        sentinel.prev.next = curr;
        curr.prev = sentinel.prev;
        sentinel.prev = curr;
        curr.next = sentinel;
        size = size + 1;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        // remember first node
        Node first = sentinel.next;
        // cut first't prev
        first.prev = null;
        // sentinel connect the second
        sentinel.next = first.next;
        sentinel.next.prev = sentinel;
        // cut first's next
        first.next = null;
        // return first's item
        size = size - 1;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        last.prev = null;
        last.next = null;
        size = size - 1;
        return last.item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node pointer = sentinel.next;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.item;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }


    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    public T getRecursive(int index) {
        if (size == 0 || (index >= size)) {
            return null;
        }
        LinkedListDeque copy = new LinkedListDeque<>(this);
        Node pointer = copy.sentinel.next;
        if (index == 0) {
            return pointer.item;
        }
        copy.removeFirst();
        return (T) copy.getRecursive(index - 1);
    }
}
