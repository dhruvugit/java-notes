package org.dhruvnotes.otherJavaCodes;

/*********************************.  STEPS.  *************************
 * 1. Create the basic HashMapImpl Class with generic K, V
 * Make Node class, here we named it entry with Entry<K, V>, it will have key value and next node (as it's linkedlist )
 * Initialize the things in Entry class in it's constructor
 * Now deinfe, buckets (as array of Entry), size = 0, Initial Capacity, LoadFactor
 * Now write constructor for HashMapImpl class initialize it with bucket and and initial map size.
 * Now write method for getHash for Key, ----> math.abs(key.hasCode()%bucketLength)
 * Write method for : get, put, remove, rehash, getSize()
*
* */



/**
 * A generic HashMap implementation using separate chaining for collision handling.
 * Supports put, get, remove, and rehashing operations.
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public class HashMapImpl<K, V> {
    /**
     * Inner class to represent a key-value pair entry in the HashMap.
     */
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        /**
         * Constructs an entry with a key and value.
         * @param key the key
         * @param value the value
         */
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] buckets;
    private int size;
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * Constructs an empty HashMap with initial capacity.
     */
    @SuppressWarnings("unchecked")
    public HashMapImpl() {
        buckets = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Computes the bucket index for a given key.
     * @param key the key to hash
     * @return the bucket index
     */
    private int getHash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    /**
     * Adds or updates a key-value pair in the HashMap.
     * Triggers rehashing if load factor exceeds threshold.
     * @param key the key
     * @param value the value
     */
    public void put(K key, V value) {
        if ((float) (size + 1) / buckets.length > LOAD_FACTOR) {
            rehash();
        }
        int index = getHash(key);
        if (buckets[index] == null) {
            buckets[index] = new Entry<>(key, value);
            size++;
            return;
        }
        Entry<K, V> curr = buckets[index], prev = null;
        while (curr != null) {
            if (curr.key.equals(key)) {
                curr.value = value;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
        prev.next = new Entry<>(key, value);
        size++;
    }

    /**
     * Retrieves the value associated with a key.
     * @param key the key to look up
     * @return the value, or null if not found
     */
    public V get(K key) {
        int index = getHash(key);
        Entry<K, V> curr = buckets[index];
        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr.value;
            }
            curr = curr.next;
        }
        return null;
    }

    /**
     * Removes a key-value pair from the HashMap.
     * @param key the key to remove
     * @return true if removed, false if not found
     */
    public boolean remove(K key) {
        int index = getHash(key);
        Entry<K, V> curr = buckets[index], prev = null;
        while (curr != null) {
            if (curr.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    /**
     * Doubles the capacity and rehashes all entries to maintain performance.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = (Entry<K, V>[]) new Entry[oldBuckets.length * 2];
        size = 0;
        for (Entry<K, V> bucket : oldBuckets) {
            Entry<K, V> curr = bucket;
            while (curr != null) {
                put(curr.key, curr.value);
                curr = curr.next;
            }
        }
    }

    /**
     * Returns the number of key-value pairs in the HashMap.
     * @return the size
     */
    public int size() {
        return size;
    }
}