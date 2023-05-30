package org.benchmark;

class HashTableBenchmark<K, V> {
    public void runBenchmark() {
        System.out.println("PseudoHash:");// SHA256
        HashFunc<K> sha256= new SHA256<>();
        PseudoHash<K, V> hashTableSHA256 = new PseudoHash<>(sha256, 1000);
        HashFunc<K> md5 = new MD5<>();
        PseudoHash<K, V> hashTableMD5 = new PseudoHash<>(md5, 1000);
        HashFunc<K> crc32 = new CustomCRC32<>();
        PseudoHash<K, V> hashTableCRC32 = new PseudoHash<>(crc32, 1000);
        // Генерация большого количества операций
        int numOperations = 10000;

        System.out.println("\n Present SHA256:");// SHA256
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            V value = (V) Integer.valueOf(i);
            hashTableSHA256.insert(key, value);
        }
        long endTime = System.currentTimeMillis();
        double insertTime = (endTime - startTime) / 1000.0;
        System.out.println("Insertion time: " + insertTime + " seconds");

        // Измерение времени выполнения операций поиска
        startTime = System.currentTimeMillis();
        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            V value = hashTableSHA256.search(key);
        }
        endTime = System.currentTimeMillis();
        double searchTime = (endTime - startTime) / 1000.0;
        System.out.println("Search time: " + searchTime + " seconds");

        // Измерение времени выполнения операций удаления
        startTime = System.currentTimeMillis();
        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            hashTableSHA256.remove(key);
        }
        endTime = System.currentTimeMillis();
        double removeTime = (endTime - startTime) / 1000.0;
        System.out.println("Removal time: " + removeTime + " seconds");
//


        System.out.println("\n Present MD5:");// MD5
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            V value = (V) Integer.valueOf(i);
            hashTableMD5.insert(key, value);
        }
        long endTime2 = System.currentTimeMillis();
        double insertTime2 = (endTime2 - startTime2) / 1000.0;
        System.out.println("Insertion time: " + insertTime2 + " seconds");

        // Измерение времени выполнения операций поиска
        startTime2 = System.currentTimeMillis();
        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            V value = hashTableMD5.search(key);
        }
        endTime2 = System.currentTimeMillis();
        double searchTime2 = (endTime2 - startTime2) / 1000.0;
        System.out.println("Search time: " + searchTime2 + " seconds");

        // Измерение времени выполнения операций удаления
        startTime2 = System.currentTimeMillis();
        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            hashTableMD5.remove(key);
        }
        endTime2 = System.currentTimeMillis();
        double removeTime2 = (endTime2 - startTime2) / 1000.0;
        System.out.println("Removal time: " + removeTime2 + " seconds");



        System.out.println("\n Present CRC32:");// CRC32
        long startTime3 = System.currentTimeMillis();
        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            V value = (V) Integer.valueOf(i);
            hashTableCRC32.insert(key, value);
        }
        long endTime3 = System.currentTimeMillis();
        double insertTime3 = (endTime3 - startTime3) / 1000.0;
        System.out.println("Insertion time: " + insertTime3 + " seconds");

        // Измерение времени выполнения операций поиска
        startTime3 = System.currentTimeMillis();
        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            V value = hashTableCRC32.search(key);
        }
        endTime3 = System.currentTimeMillis();
        double searchTime3 = (endTime3 - startTime3) / 1000.0;
        System.out.println("Search time: " + searchTime3 + " seconds");

        // Измерение времени выполнения операций удаления
        startTime3 = System.currentTimeMillis();
        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            hashTableCRC32.remove(key);
        }
        endTime3 = System.currentTimeMillis();
        double removeTime3 = (endTime3 - startTime3) / 1000.0;
        System.out.println("Removal time: " + removeTime3 + " seconds");
    }
}