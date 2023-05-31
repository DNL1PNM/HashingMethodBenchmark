package org.benchmark;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.nanoTime;

public class ChainingBenchmark <K,V>{
    public void run() {
        System.out.println("Chaining");
        HashFunc<K> sha256 = new CustomSHA256<>();
        HashTable<K, V> hashTableSHA256 = new ChainingHash<>(sha256, 1000);
        HashFunc<K> md5 = new CustomMD5<>();
        HashTable<K, V> hashTableMD5 = new ChainingHash<>(md5, 1000);
        HashFunc<K> crc32 = new CustomCRC32<>();
        HashTable<K, V> hashTableCRC32 = new ChainingHash<>(crc32, 1000);
        int numOperations = 1_000;
        runOperationBenchmark("Insertion", numOperations, hashTableSHA256, hashTableMD5, hashTableCRC32);
        runOperationBenchmark("Search", numOperations, hashTableSHA256, hashTableMD5, hashTableCRC32);
        runOperationBenchmark("Removal", numOperations, hashTableSHA256, hashTableMD5, hashTableCRC32);
    }

    private void runOperationBenchmark(String operation, int numOperations, HashTable<K, V> hashTableSHA256,
                                       HashTable<K, V> hashTableMD5, HashTable<K, V> hashTableCRC32) {
        System.out.println("\n" + operation + " Benchmark:");
// Операция "insertion"
        long totalSHA256Insertion = 0;
        long totalMD5Insertion = 0;
        long totalCRC32Insertion = 0;

// Операция "search"
        long totalSHA256Search = 0;
        long totalMD5Search = 0;
        long totalCRC32Search = 0;

// Операция "removal"
        long totalSHA256Removal = 0;
        long totalMD5Removal = 0;
        long totalCRC32Removal = 0;

        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            V value = (V) Integer.valueOf(i);

            // Операция "insertion"
            long startTimeSHA256Insertion = nanoTime();
            hashTableSHA256.insert(key, value);
            long endTimeSHA256Insertion = nanoTime();
            totalSHA256Insertion += (endTimeSHA256Insertion - startTimeSHA256Insertion);

            long startTimeMD5Insertion = nanoTime();
            hashTableMD5.insert(key, value);
            long endTimeMD5Insertion = nanoTime();
            totalMD5Insertion += (endTimeMD5Insertion - startTimeMD5Insertion);

            long startTimeCRC32Insertion = nanoTime();
            hashTableCRC32.insert(key, value);
            long endTimeCRC32Insertion = nanoTime();
            totalCRC32Insertion += (endTimeCRC32Insertion - startTimeCRC32Insertion);

            // Операция "search"
            long startTimeSHA256Search = nanoTime();
            hashTableSHA256.search(key);
            long endTimeSHA256Search = nanoTime();
            totalSHA256Search += (endTimeSHA256Search - startTimeSHA256Search);

            long startTimeMD5Search = nanoTime();
            hashTableMD5.search(key);
            long endTimeMD5Search = nanoTime();
            totalMD5Search += (endTimeMD5Search - startTimeMD5Search);

            long startTimeCRC32Search = nanoTime();
            hashTableCRC32.search(key);
            long endTimeCRC32Search = nanoTime();
            totalCRC32Search += (endTimeCRC32Search - startTimeCRC32Search);

            // Операция "removal"
            long startTimeSHA256Removal = nanoTime();
            hashTableSHA256.remove(key);
            long endTimeSHA256Removal = nanoTime();
            totalSHA256Removal += (endTimeSHA256Removal - startTimeSHA256Removal);

            long startTimeMD5Removal = nanoTime();
            hashTableMD5.remove(key);
            long endTimeMD5Removal = nanoTime();
            totalMD5Removal += (endTimeMD5Removal - startTimeMD5Removal);

            long startTimeCRC32Removal = nanoTime();
            hashTableCRC32.remove(key);
            long endTimeCRC32Removal = nanoTime();
            totalCRC32Removal += (endTimeCRC32Removal - startTimeCRC32Removal);
        }

        double avgTimeSHA256Insertion = totalSHA256Insertion / (double) numOperations;
        double avgTimeMD5Insertion = totalMD5Insertion / (double) numOperations;
        double avgTimeCRC32Insertion = totalCRC32Insertion / (double) numOperations;

        double avgTimeSHA256Search = totalSHA256Search / (double) numOperations;
        double avgTimeMD5Search = totalMD5Search / (double) numOperations;
        double avgTimeCRC32Search = totalCRC32Search / (double) numOperations;

        double avgTimeSHA256Removal = totalSHA256Removal / (double) numOperations;
        double avgTimeMD5Removal = totalMD5Removal / (double) numOperations;
        double avgTimeCRC32Removal = totalCRC32Removal / (double) numOperations;

        System.out.println("CSHA256 : \n" +
                "Insertion time: " + avgTimeSHA256Insertion + " nanoseconds\n" +
                "Search time: " + avgTimeSHA256Search + " nanoseconds\n" +
                "Removal time: " + avgTimeSHA256Removal + " nanoseconds");

        System.out.println("MD5 : \n" +
                "Insertion time: " + avgTimeMD5Insertion + " nanoseconds\n" +
                "Search time: " + avgTimeMD5Search + " nanoseconds\n" +
                "Removal time: " + avgTimeMD5Removal + " nanoseconds");

        System.out.println("CRC32 :\n" +
                "Insertion time: " + avgTimeCRC32Insertion + " nanoseconds\n" +
                "Search time: " + avgTimeCRC32Search + " nanoseconds\n" +
                "Removal time: " + avgTimeCRC32Removal + " nanoseconds");
    }
}