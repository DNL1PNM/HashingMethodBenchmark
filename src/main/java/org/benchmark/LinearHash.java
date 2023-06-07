package org.benchmark;
import java.util.LinkedList;
import java.util.List;
public class LinearHash<K, V> extends HashTable<K, V> {
    private List<Entry<K, V>> table;

    public LinearHash(HashFunc<K> hashFunction, int size) {
        createTable(size);
        setHashFunction(hashFunction, size);
    }

    @Override
    public void insert(K key, V value) {
        int hash = getHashFunction().hash(key);
        int index = Math.abs(hash) % getSize();

        while (table.get(index) != null) {
            index = (index + 1) % getSize();
        }

        table.set(index, new Entry<>(key, value));
    }

    @Override
    public void remove(K key) {
        int hash = getHashFunction().hash(key);
        int index = Math.abs(hash) % getSize();

        while (table.get(index) != null && !table.get(index).getKey().equals(key)) {
            index = (index + 1) % getSize();
        }

        if (table.get(index) != null && table.get(index).getKey().equals(key)) {
            table.set(index, null);
        }
    }
    public V search(K key) {
        int hash = getHashFunction().hash(key);
        int index = Math.abs(hash) % getSize();

        while (table.get(index) != null && !table.get(index).getKey().equals(key)) {
            index = (index + 1) % getSize();
        }

        if (table.get(index) != null && table.get(index).getKey().equals(key)) {
            return table.get(index).getValue();
        }

        return null;
    }
    private void createTable(int size) {
        table = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            table.add(null);
        }
    }
}