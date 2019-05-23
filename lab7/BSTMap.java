import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    private class Node {
        private K key;
        private V value;
        private int size;
        private Node right, left;

        Node(K key, V value, int size) {
            this.size = size;
            this.key = key;
            this.value = value;
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root.right = null;
        root.left = null;
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("illegal key");
        }
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (key == null) {
            throw new IllegalArgumentException("illegal key");
        }
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        }
        if (cmp > 0) {
            return get(x.right, key);
        }
        return x.value;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("illegal key");
        }
        if (value == null) {
            remove(key);
            return;
        }
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void printInOrder() {
        printing(root);
    }

    private void printing(Node x) {
        if (x == null) {
            return;
        }

        printing(x.left);

        System.out.print(x.key);
        System.out.print(x.value);
        System.out.println();

        printing(x.right);

    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> newSet = new HashSet<K>();
        getKeys(root, newSet);
        return newSet;
    }

    private void getKeys(Node x, Set<K> set) {
        if (x == null) {
            return;
        }
        if (x.left != null) {
            getKeys(x.left, set);
        }
        if (x.right != null) {
            getKeys(x.right, set);
        }
        set.add(x.key);
        return;
    }


    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new UnsupportedOperationException("call remove() with a null key");
        }
        V returnV = get(key);
        root = remove(root, key);
        return returnV;
    }

    private Node remove(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = remove(x.left, key);
        } else if (cmp > 0) {
            x.right = remove(x.right, key);
        } else if (cmp == 0) {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            Node t = min(x.right);
            x.key = t.key;
            x.value = t.value;
            remove(t, t.key);
        }
        x.size = size(x.right) + size(x.left) + 1;
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }


    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("no");
    }

    public Iterator<K> iterator() {
        return new BSTMiterator();
    }

    private class BSTMiterator implements Iterator<K> {
        private int count;
        Queue ll = keys(min(root).key, max(root).key);

        public BSTMiterator() {
            count = root.size;
        }
        public boolean hasNext() {
            return count != 0;
        }
        public K next() {
           K returnK = (K) ll.remove();
           count -= 1;
           return returnK;
        }
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    public Iterable<K> keys() {
        if (root.size == 0) {
            return new LinkedList<K>();
        }
        return keys(min(root).key, max(root).key);
    }

    public Queue<K> keys(K lo, K hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<K> ll = new LinkedList<K>();
        keys(ll, lo, hi, root);
        return ll;
    }

    private void keys(Queue queue, K lo, K hi, Node x) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(queue, lo, hi, x.left);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.add(x.key);
        }
        if (cmphi > 0) {
            keys(queue, lo, hi, x.right);
        }
    }


}
