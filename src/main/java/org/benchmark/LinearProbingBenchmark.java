package org.benchmark;

public class LinearProbingBenchmark<K, V> {
    public void run() {
        HashFunc<K> sha256 = new CustomSHA256<>();
        LinearProbingHash<K, V> hashTableSHA256 = new LinearProbingHash<>(sha256, 100);
        HashFunc<K> md5 = new CustomMD5<>();
        LinearProbingHash<K, V> hashTableMD5 = new LinearProbingHash<>(md5, 100);
        HashFunc<K> crc32 = new CustomCRC32<>();
        LinearProbingHash<K, V> hashTableCRC32 = new LinearProbingHash<>(crc32, 100);
        int numOperations = 100;

        runOperationBenchmark("Insertion", numOperations, hashTableSHA256, hashTableMD5, hashTableCRC32);
        runOperationBenchmark("Search", numOperations, hashTableSHA256, hashTableMD5, hashTableCRC32);
        runOperationBenchmark("Removal", numOperations, hashTableSHA256, hashTableMD5, hashTableCRC32);
    }

    private void runOperationBenchmark(String operation, int numOperations,
                                       LinearProbingHash<K, V> hashTableSHA256,
                                       LinearProbingHash<K, V> hashTableMD5,
                                       LinearProbingHash<K, V> hashTableCRC32) {
        System.out.println("\n" + operation + " Benchmark:");

        long startTimeSHA256 = System.currentTimeMillis();
        long startTimeMD5 = System.currentTimeMillis();
        long startTimeCRC32 = System.currentTimeMillis();

        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            V value = (V) Integer.valueOf(i);

            switch (operation.toLowerCase()) {
                case "insertion":
                    hashTableSHA256.insert(key, value);
                    hashTableMD5.insert(key, value);
                    hashTableCRC32.insert(key, value);
                    break;
                case "search":
                    hashTableSHA256.search(key);
                    hashTableMD5.search(key);
                    hashTableCRC32.search(key);
                    break;
                case "removal":
                    hashTableSHA256.remove(key);
                    hashTableMD5.remove(key);
                    hashTableCRC32.remove(key);
                    break;
                default:
                    System.out.println("Invalid operation: " + operation);
                    return;
            }
        }

        long endTimeSHA256 = System.currentTimeMillis();
        long endTimeMD5 = System.currentTimeMillis();
        long endTimeCRC32 = System.currentTimeMillis();

        double timeSHA256 = (endTimeSHA256 - startTimeSHA256) / 1000.0;
        double timeMD5 = (endTimeMD5 - startTimeMD5) / 1000.0;
        double timeCRC32 = (endTimeCRC32 - startTimeCRC32) / 1000.0;

        System.out.println("СustSHA256 " + operation + " time: " + timeSHA256 + " seconds");
        System.out.println("CustomMD5 " + operation + " time: " + timeMD5 + " seconds");
        System.out.println("CRC32 " + operation + " time: " + timeCRC32 + " seconds");
    }
}