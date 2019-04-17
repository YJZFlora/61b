
public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int FACTOR = 2;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int posi = nextFirst + 1;
        posi = moveCursor(posi, index);
        return items[posi];
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = moveCursor(nextFirst, -1);
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = moveCursor(nextLast, 1);
        size += 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (size < items.length) { // cut
            if (nextFirst > nextLast) {  // nl nf
                System.arraycopy(items, nextFirst + 1, a, 0, (size - nextLast));
                for (int i = 0; i < nextLast; i++) {
                    a[size - nextLast + i] = items[i];
                }
            } else { // nf   ... nl
                System.arraycopy(items, nextFirst + 1, a, 0, size);
            }
        } else { // widden
            System.arraycopy(items, nextLast, a, 0, (size - nextLast));
            for (int i = 0; i < nextLast; i++) {
                a[size - nextLast + i] = items[i];
            }
        }

        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }


    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        manageMemory();
        nextFirst = moveCursor(nextFirst, 1);
        size = size - 1;
        return items[nextFirst];
    }


    public T removeLast() {
        if (size == 0) {
            return null;
        }
        manageMemory();
        nextLast = moveCursor(nextLast, -1);
        size = size - 1;
        return items[nextLast];
    }

    public void printDeque() {
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    private int moveCursor(int cursor, int direction) {
        cursor = cursor + direction;
        if (cursor == -1) {
            cursor = items.length - 1;
        }
        if (cursor >= items.length) {
            cursor = cursor - items.length;
        }
        return cursor;
    }

    private void manageMemory() {
        double R = (double) size / (double) items.length;
        if (items.length > 15) {
            if (R < 0.25) {
                resize(items.length / 2);
            }
        }
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.items.length];
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        System.arraycopy(other.items, 0, this.items, 0, other.items.length);
    }

}
