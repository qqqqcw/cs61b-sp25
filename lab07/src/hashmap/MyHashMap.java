package hashmap;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size = 0;
    private int initialCapacity;
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        buckets = (Collection<Node>[]) new Collection[initialCapacity];
        size = 0;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

    private int indexFor(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    private Node findNode(Collection<Node> bucket, K key) {
        if (bucket == null) {
            return null;
        }
        for(Node n : bucket) {
            if (n.key.equals(key)) {
                return n;
            }
        }
        return null;
    }

    private void resize(int capacity) {
        Collection<Node>[] oldBuckets = buckets;
        this.buckets = (Collection<Node>[]) new Collection[capacity];
        size = 0;
        for (Collection<Node> Bucket : oldBuckets) {
            if (Bucket != null) {
                for(Node node : Bucket) {
                    put(node.key, node.value);
                }
            }
        }
    }
    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if((size + 1.0) / buckets.length > loadFactor) {
            resize(buckets.length * 2);
        }
        int index = indexFor(key);
        if (buckets[index] == null) {
            buckets[index] = createBucket();
        }
        Node existing = findNode(buckets[index], key);
        if(existing == null) {
            buckets[index].add(new Node(key, value));
            size++;
        } else {
            existing.value = value;
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
        int index = indexFor(key);
        if(buckets[index] == null) {
            return null;
        }
        Node existing = findNode(buckets[index], key);
        if(existing == null) {
            return null;
        }
        return existing.value;
    }


    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        int index = indexFor(key);
        if(buckets[index] == null) {
            return false;
        }
        Node existing = findNode(buckets[index], key);
        if(existing == null) {
            return false;
        }
        return true;
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
        this.size = 0;
        this.buckets = (Collection<Node>[]) new Collection[initialCapacity];
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
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
        throw new UnsupportedOperationException();
    }



}
