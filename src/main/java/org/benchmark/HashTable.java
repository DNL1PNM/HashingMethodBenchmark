package org.benchmark;

public abstract class HashTable<K, V> {

    protected HashFunc<K> hashFunction;
    protected int size ;

    public abstract void insert(K key, V value);

    public abstract void remove(K key);

    public abstract V search(K key);

    public int getSize() {
        return size;
    }

    protected HashFunc<K> getHashFunction() {
        return hashFunction;
    }

    protected void setHashFunction(HashFunc<K> hashFunction, int size) {
        this.hashFunction = hashFunction;
    }

    protected static class Entry<K, V> {
        private final K key;
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