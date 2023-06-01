package org.benchmark;

import java.util.ArrayList;
import java.util.List;

public class LinearProbingHash<K, V> extends HashTable<K,V> {
    private static final int DEFAULT_SIZE = 1000;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private Entry<K, V>[] table;
    private int size;
    private int threshold;

    public LinearProbingHash(HashFunc<K> hashFunction) {
        this(hashFunction, DEFAULT_SIZE);
    }

    public LinearProbingHash(HashFunc<K> hashFunction, int size) {
        super(size);
        setHashFunction(hashFunction);
        this.size = size;
        this.threshold = (int) (size * DEFAULT_LOAD_FACTOR);
        this.table = new Entry[size];
    }

    @Override
    public void insert(K key, V value) {
        int index = getIndex(key);
        while (table[index] != null && table[index].isDeleted()) {
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
            table[index].setDeleted(true);
            size--;
        }
    }

    @Override
    public void search(K key) {
        int index = findIndex(key);
        if (index != -1 && !table[index].isDeleted()) {
            return;
        }
    }

    @Override
    protected List<List<HashTable.Entry<K, V>>> createTable(int size) {
        List<List<HashTable.Entry<K, V>>> table = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            table.add(new ArrayList<>());
        }
        return table;
    }

    @Override
    protected void resizeTable() {
        int newSize = size * 2;
        Entry<K, V>[] newTable = new Entry[newSize];
        threshold = (int) (newSize * DEFAULT_LOAD_FACTOR);
        for (Entry<K, V> entry : table) {
            if (entry != null && !entry.isDeleted()) {
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

    private int getIndex(K key) {
        int hash = getHashFunction().hash(key);
        return Math.abs(hash) % size;
    }

    private int findIndex(K key) {
        int index = getIndex(key);
        int startIndex = index;
        while (table[index] != null) {
            if (!table[index].isDeleted() && table[index].getKey().equals(key)) {
                return index;
            }
            index = (index + 1) % size;
            if (index == startIndex) {
                break;
            }
        }
        return -1;
    }

    private static class Entry<K, V> {
        private final K key;
        private final V value;
        private boolean deleted;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.deleted = false;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }
    }
}