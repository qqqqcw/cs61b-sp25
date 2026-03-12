import javax.sql.rowset.spi.TransactionalWriter;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node (K key, V value) {
            this.key = key;
            this.value = value;
        }

        public int compare (K otherK) {
            return ((Comparable<K>) this.key).compareTo(otherK);
        }
    }
    private Node root;
    private int size = 0;


    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put (K key, V value) {
        if (size == 0) {
            size += 1;
            root = new Node(key, value);
            return;
        }
        Node TempNode = root;
        while (true) {
            if (TempNode.compare(key) < 0) {
                if (TempNode.right == null) {
                    TempNode.right = new Node(key, value);
                    size += 1;
                    return;
                }
                TempNode = TempNode.right;
            } else if (TempNode.compare(key) > 0) {
                if (TempNode.left == null) {
                    TempNode.left = new Node(key, value);
                    size += 1;
                    return;
                }
                TempNode = TempNode.left;
            } else {
                TempNode.value = value;
                return;
            }

        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if (this.size == 0) {
            return null;
        }
        Node TempNode = root;
        while (true) {
            if (TempNode.compare(key) < 0) {
                if (TempNode.right == null) {
                    return null;
                }
                TempNode = TempNode.right;
            } else if (TempNode.compare(key) > 0) {
                if (TempNode.left == null) {
                    return null;
                }
                TempNode = TempNode.left;
            } else {
                return TempNode.value;
            }
        }
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (this.size == 0) {
            return false;
        }
        Node TempNode = root;
        while (true) {
            if (TempNode.compare(key) < 0) {
                if (TempNode.right == null) {
                    return false;
                }
                TempNode = TempNode.right;
            } else if (TempNode.compare(key) > 0) {
                if (TempNode.left == null) {
                    return false;
                }
                TempNode = TempNode.left;
            } else {
                return true;
            }
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
