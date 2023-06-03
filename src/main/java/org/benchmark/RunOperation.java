package org.benchmark;

import static java.lang.System.nanoTime;

public class RunOperation<K, V> {
    public void runOperationBenchmark(String operation, int numOperations, HashTable<K, V> hashTableSHA256,
                                       HashTable<K, V> hashTableMD5, HashTable<K, V> hashTableCRC32) {
        System.out.println("Name Operation = " + operation + numOperations);

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
            totalSHA256Insertion += measureInsertionTime(hashTableSHA256, key, value);
            totalMD5Insertion += measureInsertionTime(hashTableMD5, key, value);
            totalCRC32Insertion += measureInsertionTime(hashTableCRC32, key, value);

            // Операция "search"
            totalSHA256Search += measureSearchTime(hashTableSHA256, key);
            totalMD5Search += measureSearchTime(hashTableMD5, key);
            totalCRC32Search += measureSearchTime(hashTableCRC32, key);

            // Операция "removal"
            totalSHA256Removal += measureRemovalTime(hashTableSHA256, key);
            totalMD5Removal += measureRemovalTime(hashTableMD5, key);
            totalCRC32Removal += measureRemovalTime(hashTableCRC32, key);
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

        System.out.println("SHA256 : \n" +
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
                "Removal time: " + avgTimeCRC32Removal + " nanoseconds\n");
    }
    private long measureInsertionTime(HashTable<K, V> hashTable, K key, V value) {
        long startTime = nanoTime();
        hashTable.insert(key, value);
        long endTime = nanoTime();
        return endTime - startTime;
    }

    private long measureSearchTime(HashTable<K, V> hashTable, K key) {
        long startTime = nanoTime();
        hashTable.search(key);
        long endTime = nanoTime();
        return endTime - startTime;
    }

    private long measureRemovalTime(HashTable<K, V> hashTable, K key) {
        long startTime = nanoTime();
        hashTable.remove(key);
        long endTime = nanoTime();
        return endTime - startTime;
    }
}
