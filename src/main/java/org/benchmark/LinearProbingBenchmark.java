package org.benchmark;

public class LinearProbingBenchmark<K, V> extends RunOperation<K, V>{
    public void run() {
        HashFunc<K> sha256 = new CustomSHA256<>();
        HashTable<K, V> hashTableSHA256 = new LinearProbingHash<>(sha256, 1000);

        HashFunc<K> md5 = new CustomMD5<>();
        HashTable<K, V> hashTableMD5 = new LinearProbingHash<>(md5, 1000);

        HashFunc<K> crc32 = new CustomCRC32<>();
        HashTable<K, V> hashTableCRC32 = new LinearProbingHash<>(crc32, 1000);

        int numOperations = 10;

        runOperationBenchmark("LinearProbing :", numOperations, hashTableSHA256, hashTableMD5, hashTableCRC32);
    }
}