package org.benchmark;
import static java.lang.System.nanoTime;

public class Benchmark<K,V> {
    private int tableSize = 1_007;
    public void run (HashTable<K,V> hashTable, int tableSize){

        runOperationBenchmark(tableSize,hashTable);
    }
    public int getSize (){
        return  tableSize;
    }
    public void runOperationBenchmark(int numOperations, HashTable<K, V>customHashTable) {

        long totalInsertion = 0;
        long totalSearch = 0;
        long totalRemoval = 0;

        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            V value = (V) Integer.valueOf(i);
            System.out.println(key);
            totalInsertion += measureInsert(customHashTable, key, value);
            totalSearch += measureSearch(customHashTable, key);

        }
        for (int i = 0; i < numOperations; i++) {
            K key = (K) ("key" + i);
            V value = (V) Integer.valueOf(i);
            totalRemoval += measureRemoval(customHashTable, key);

        }

        double avgTimeInsertion = totalInsertion / (double) numOperations;

        double avgTimeSearch = totalSearch / (double) numOperations;

        double avgTimeRemoval = totalRemoval / (double) numOperations;

        System.out.println("Function = " + customHashTable.getHashFunction() +
                "\nInsertion time: " + avgTimeInsertion + " nanoseconds\n" +
                "Search time: " + avgTimeSearch + " nanoseconds\n" +
                "Removal time: " + avgTimeRemoval + " nanoseconds\n");

    }
    private long measureInsert(HashTable<K, V> customHashTable, K key, V value) {
        long startTime = nanoTime();
        customHashTable.insert(key, value);
        long endTime = nanoTime();
        return endTime - startTime;
    }
    private long measureSearch(HashTable<K, V> hashTable, K key) {
        long startTime = nanoTime();
        hashTable.search(key);
        long endTime = nanoTime();
        return endTime - startTime;
    }

    private long measureRemoval(HashTable<K, V> hashTable, K key) {
        long startTime = nanoTime();
        hashTable.remove(key);
        long endTime = nanoTime();
        return endTime - startTime;
    }
}