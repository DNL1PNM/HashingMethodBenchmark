package org.benchmark;

import java.util.List;

public abstract class HashTable<K, V> {
    private List<List<Entry<K, V>>> table;
    private int size;
    private HashFunc<K> hashFunction;

    public HashTable(int initialSize) {
        table = createTable(initialSize);
        size = 1;
    }

    public abstract void insert(K key, V value);

    public abstract void remove(K key);

    public abstract void search(K key);

    protected abstract List<List<Entry<K, V>>> createTable(int size);

    protected abstract void resizeTable();

    protected List<List<Entry<K, V>>> getTable() {
        return table;
    }

    protected void setTable(List<List<Entry<K, V>>> table) {
        this.table = table;
    }

    protected int getSize() {
        return size;
    }

    protected void setSize(int size) {
        this.size = size;
    }

    protected HashFunc<K> getHashFunction() {
        return hashFunction;
    }

    protected void setHashFunction(HashFunc<K> hashFunction) {
        this.hashFunction = hashFunction;
    }

    protected static class Entry<K, V> {
        private K key;
        private V value;

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

        public void setValue(V value) {
            this.value = value;
        }
    }
}