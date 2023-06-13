package org.benchmark;

public class Tombstone<K, V> extends HashTable.Entry<K, V> {
    private static final Tombstone INSTANCE = new Tombstone();

    private Tombstone() {
        super(null, null);
    }

    public static <K, V> Tombstone<K, V> getInstance() {
        return INSTANCE;
    }
}
