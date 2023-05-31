package org.benchmark;

import java.util.LinkedList;
import java.util.List;

public class ChainingHash<K, V> extends HashTable<K, V> {
    public ChainingHash(HashFunc<K> hashFunction, int size) {
        super(size);
        setHashFunction(hashFunction);
        createTable(size);
    }

    @Override
    public void insert(K key, V value) {
        int hash = getHashFunction().hash(key);
        int index = Math.abs(hash) % getSize();
        List<Entry<K, V>> chain = getTable().get(index);
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
        List<Entry<K, V>> chain = getTable().get(index);
        for (Entry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                chain.remove(entry);
                return;
            }
        }
    }

    @Override
    public void search(K key) {
        int hash = getHashFunction().hash(key);
        int index = Math.abs(hash) % getSize();
        List<Entry<K, V>> chain = getTable().get(index);
        for (Entry<K, V> entry : chain) {
            if (entry.getKey().equals(key)) {
                return;
            }
        }
    }

    @Override
    protected List<List<Entry<K, V>>> createTable(int size) {
        List<List<Entry<K, V>>> table = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<>());
        }
        return table;
    }

    @Override
    protected void resizeTable() {
        int newSize = getSize() * 2;
        List<List<Entry<K, V>>> newTable = createTable(newSize);

        for (List<Entry<K, V>> chain : getTable()) {
            for (Entry<K, V> entry : chain) {
                int newIndex = Math.abs(getHashFunction().hash(entry.getKey())) % newSize;
                newTable.get(newIndex).add(entry);
            }
        }

        setTable(newTable);
        setSize(newSize);
    }
}