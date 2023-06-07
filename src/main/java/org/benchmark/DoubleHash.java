package org.benchmark;
import java.util.LinkedList;
import java.util.List;

public class DoubleHash<K, V> extends HashTable<K, V> {
    private List<Entry<K, V>> table;

    public DoubleHash(HashFunc<K> hashFunction, int size) {
        createTable(size);
        setHashFunction(hashFunction, size);
    }

    @Override
    public void insert(K key, V value) {
        int hash1 = getHashFunction().hash(key);
        int hash2 = hash2(key);
        int index = Math.abs(hash1) % getSize();

        while (table.get(index) != null) {
            index = (index + Math.abs(hash2)) % getSize();
        }

        table.set(index, new Entry<>(key, value));
    }

    @Override
    public void remove(K key) {
        int hash1 = getHashFunction().hash(key);
        int hash2 = hash2(key);
        int index = Math.abs(hash1) % getSize();

        while (table.get(index) != null && !table.get(index).getKey().equals(key)) {
            index = (index + Math.abs(hash2)) % getSize();
        }

        if (table.get(index) != null && table.get(index).getKey().equals(key)) {
            table.set(index, null);
        }
    }

    public V search(K key) {
        int hash1 = getHashFunction().hash(key);
        int hash2 = hash2(key);
        int index = Math.abs(hash1) % getSize();

        while (table.get(index) != null && !table.get(index).getKey().equals(key)) {
            index = (index + Math.abs(hash2)) % getSize();
        }

        if (table.get(index) != null && table.get(index).getKey().equals(key)) {
            return table.get(index).getValue();
        }

        return null;
    }

    private int hash2(K key) {
        int prime = 31;
        int hash = key.hashCode() % prime;
        return prime - hash;
    }

    private void createTable(int size) {
        table = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            table.add(null);
        }
    }
}