public interface Deque<T> {

    default boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    };

    int size();

    T get(int index);

    void addFirst(T item);

    void addLast(T item);

    T removeFirst();

    T removeLast();

    void printDeque();

}
