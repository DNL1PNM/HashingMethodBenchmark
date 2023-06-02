package org.benchmark;
import java.lang.reflect.Array;

public class LinearProbingHash<K, V> extends HashTable<K, V> {
    private static final int DEFAULT_SIZE = 1000;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private Entry<K, V>[] table;
    private int size;
    private int threshold;

    public LinearProbingHash(HashFunc<K> hashFunction) {
        this(hashFunction, DEFAULT_SIZE);
    }

    public LinearProbingHash(HashFunc<K> hashFunction, int size) {
        setHashFunction(hashFunction, size);
        this.size = size;
        this.threshold = (int) (size * DEFAULT_LOAD_FACTOR);
        this.table = createEntryArray(size);
    }

    @Override
    public void insert(K key, V value) {
        int index = getIndex(key);
        while (table[index] != null) {
            index = (index + 1) % size;
        }
        table[index] = new Entry<>(key, value);
        if (++size > threshold) {
            resizeTable();
        }
    }

    @Override
    public void remove(K key) {
        int index = findIndex(key);
        if (index != -1) {
            table[index] = null;
            size--;
            condenseTable(index);
        }
    }

    @Override
    public V search(K key) {
        int index = findIndex(key);
        if (index != -1) {
            return table[index].getValue();
        }
        return null;
    }

    private int getIndex(K key) {
        int hash = getHashFunction().hash(key);
        return Math.abs(hash) % size;
    }

    private int findIndex(K key) {
        int index = getIndex(key);
        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                return index;
            }
            index = (index + 1) % size;
        }
        return -1;
    }

    private void resizeTable() {
        int newSize = size * 2;
        Entry<K, V>[] newTable = createEntryArray(newSize);
        threshold = (int) (newSize * DEFAULT_LOAD_FACTOR);
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                int index = getIndex(entry.getKey());
                while (newTable[index] != null) {
                    index = (index + 1) % newSize;
                }
                newTable[index] = entry;
            }
        }
        table = newTable;
        size = newSize;
    }

    private void condenseTable(int startIndex) {
        int index = (startIndex + 1) % size;
        while (table[index] != null) {
            Entry<K, V> entry = table[index];
            table[index] = null;
            size--;
            insert(entry.getKey(), entry.getValue());
            index = (index + 1) % size;
        }
    }

    @SuppressWarnings("unchecked")
    private Entry<K, V>[] createEntryArray(int size) {
        return (Entry<K, V>[]) Array.newInstance(Entry.class, size);
    }

    private static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

    }
}