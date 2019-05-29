import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private double loadFactor;
    private int size;  // number of key-value pairs
    private ArrayList<HashNode<K, V>> bins;
    private int m; // hash table size

    // constructors
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.m = initialSize;
        if (initialSize < 1 || loadFactor <= 0.0) {
            throw new IllegalArgumentException();
        }
        bins = new ArrayList<HashNode<K, V>>(initialSize);
        for (int i = 0; i < m; i++) {
            bins.add(null);
        }
        this.loadFactor = loadFactor;
    }

    private class HashNode<K, V> {
        K key;
        V value;
        HashNode<K, V> next;

        HashNode (K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        HashNode (K key, V value, HashNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public K getKey() {
            return key;
        }
        public  V getValue() {
            return value;
        }
        public V setValue(V x) {
            V old = value;
            value = x;
            return old;
        }
        public int HashCode() {
            return hashCode();
        }
    }

    /* make hashcode
    * " & 0x7fffffff " is making the number to be positive, because hashCode() may return negative number*/
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        MyHashMap<K, V> newmap = new MyHashMap<>();
        this.bins = newmap.bins;
        this.m = newmap.m;
        this.size = newmap.size;
    }


    @Override
    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("don't put null key");
        }
        int i = hash(key);
        HashNode<K, V> head = bins.get(i);
        while (head != null) {
            if (head.key.equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    @Override
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("don't put null key");
        }
        int i = hash(key);
        HashNode<K, V> head = bins.get(i);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    @Override
    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    @Override
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     * If the same key is inserted more than once, the value should be updated each time.
     * You can assume null keys will never be inserted.
     */
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("don't put null key");
        }
        int i = hash(key);
        HashNode head = bins.get(i);
        // update same key
        while(head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        // add new HashNode
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        head = bins.get(i);
        newNode.next = head;
        bins.set(i, newNode);
        size++;

        double lf = (double)size / (double) m;
        if (lf > loadFactor) {
            resize(2 * m);
        }
    }

    private void resize(int newm) {
        MyHashMap<K, V> temp = new MyHashMap<K, V>(newm);
        for (int i = 0; i < m; i++) {
            HashNode<K, V> head2 = bins.get(i);
            while (head2 != null) {
                temp.put(head2.key, head2.value);
                head2 = head2.next;
            }
        }
        this.m = temp.m;
        this.size = temp.size;
        this.bins = temp.bins;
    }

    @Override
    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        HashSet<K> keyset = new HashSet<>();
        for (int i = 0; i < m; i++) {
            HashNode<K, V> head = bins.get(i);
            while (head != null) {
                keyset.add(head.key);
                head = head.next;
            }
        }
        return keyset;
    }

    @Override
    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int i = hash(key);
        V returnValue;
        HashNode<K, V> head = bins.get(i);
        if (head.key.equals(key)) {
            returnValue = head.value;
            bins.set(i, head.next);
            size--;
            return returnValue;
        }
        while (head.next != null) {
            if (head.next.key.equals(key)) {
                returnValue = head.next.value;
                HashNode<K, V> temp = head.next;
                head.next = temp.next;
                temp.next = null;
                size--;
                return returnValue;
            }
            head = head.next;
        }
        return null;
    }

    @Override
    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {

        if (key == null) {
            throw new IllegalArgumentException();
        }
        int i = hash(key);
        V returnValue;
        HashNode<K, V> head = bins.get(i);
        if (head.key.equals(key) && head.value.equals(value)) {
            returnValue = head.value;
            bins.set(i, head.next);
            size--;
            return returnValue;
        }
        while (head.next != null) {
            if (head.next.key.equals(key) && head.next.value.equals(value)) {
                returnValue = head.next.value;
                HashNode<K, V> temp = head.next;
                head.next = temp.next;
                temp.next = null;
                size--;
                return returnValue;
            }
            head = head.next;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> keyset = keySet();
        return keyset.iterator();
    }

}
