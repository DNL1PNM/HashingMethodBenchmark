package org.benchmark;
import java.util.List;
    public abstract class HashTable<K, V> {
        protected List<List<Entry<K, V>>> table;
        protected int size;

        public HashTable(int initialSize) {
            table = createTable(initialSize);
            size = 0;
        }

        public abstract void insert(K key, V value);

        public abstract void remove(K key);

        public abstract V search(K key);

        protected abstract List<List<Entry<K, V>>> createTable(int size);

        protected abstract void resizeTable();

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
