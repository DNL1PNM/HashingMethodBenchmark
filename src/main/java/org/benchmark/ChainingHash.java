package org.benchmark;
import java.util.LinkedList;

public class ChainingHash<K, V> {
    private LinkedList<Entry<K, V>>[] table;
    private HashFunc<K> hashFunction;
    private int size;

    public ChainingHash(HashFunc<K> hashFunction, int size) {
        this.hashFunction = hashFunction;
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }
    public void insert(K key, V value) {
        int hash = hashFunction.hash(key);
        int index = Math.abs(hash) % size;
        LinkedList<Entry<K, V>> chain = table[index];
        for (Entry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        chain.add(new Entry<>(key, value));
    }

    public void remove(K key) {
        int hash = hashFunction.hash(key);
        int index = Math.abs(hash) % size;
        LinkedList<Entry<K, V>> chain = table[index];
        for (Entry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                chain.remove(entry);
                return;
            }
        }
    }
    public V search(K key) {
        int hash = hashFunction.hash(key);
        int index = Math.abs(hash) % size;
        LinkedList<Entry<K, V>> chain = table[index];
        for (Entry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static class Entry<K, V> {
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