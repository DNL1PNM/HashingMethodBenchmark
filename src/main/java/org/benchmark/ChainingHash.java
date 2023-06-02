package org.benchmark;
import java.util.LinkedList;
import java.util.List;

public class ChainingHash<K, V> extends HashTable<K, V> {
    private List<List<Entry<K, V>>> table;

    public ChainingHash(HashFunc<K> hashFunction, int size) {
        createTable(size);
        setHashFunction(hashFunction, size);
    }

    @Override
    public void insert(K key, V value) {
        int hash = getHashFunction().hash(key);
        int index = Math.abs(hash) % getSize();
        List<Entry<K, V>> chain = table.get(index);
        for (Entry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        chain.add(new Entry<>(key, value));
    }

    @Override
    public void remove(K key) {
        int hash = getHashFunction().hash(key);
        int index = Math.abs(hash) % getSize();
        List<Entry<K, V>> chain = table.get(index);
        for (Entry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                chain.remove(entry);
                return;
            }
        }
    }

    public V search(K key) {
        int hash = getHashFunction().hash(key);
        int index = Math.abs(hash) % getSize();
        List<Entry<K, V>> chain = table.get(index);
        for (Entry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private void createTable(int size) {
        table = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<>());
        }
    }
}