package org.benchmark;

public class PseudoBenchmark<K, V> extends RunOperation<K, V>{
    public void run() {
        HashFunc<K> sha256 = new CustomSHA256<>();
        HashTable<K, V> hashTableSHA256 = new PseudoHash<>(sha256, 1000);

        HashFunc<K> md5 = new CustomMD5<>();
        HashTable<K, V> hashTableMD5 = new PseudoHash<>(md5, 1000);

        HashFunc<K> crc32 = new CustomCRC32<>();
        HashTable<K, V> hashTableCRC32 = new PseudoHash<>(crc32, 1000);

        int numOperations = 1_000;

        runOperationBenchmark("Pseudo :", numOperations, hashTableSHA256, hashTableMD5, hashTableCRC32);
    }
}