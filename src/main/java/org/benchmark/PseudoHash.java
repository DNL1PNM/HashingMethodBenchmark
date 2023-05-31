package org.benchmark;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

public class PseudoHash<K, V> {
    private List<List<Entry<K, V>>> table;
    private HashFunc<K> hashFunction;

    public PseudoHash(HashFunc<K> hashFunction, int size) {
        this.hashFunction = hashFunction;
        table = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            table.add(new ArrayList<>());
        }
    }

    public void insert(K key, V value) {
        int hash = hashFunction.hash(key);
        int index = Math.abs(hash) % table.size();
        List<Entry<K, V>> bucket = table.get(index);
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
    }

    public void remove(K key) {
        int hash = hashFunction.hash(key);
        int index = Math.abs(hash) % table.size();
        List<Entry<K, V>> bucket = table.get(index);
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                return;
            }
        }
        // Если ключ не найден, можно выполнить обработку отсутствия ключа, например, выбросить исключение
    }

    public V search(K key) {
        int hash = hashFunction.hash(key);
        int index = Math.abs(hash) % table.size();
        List<Entry<K, V>> bucket = table.get(index);
        for (Entry<K, V> entry : bucket) {
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