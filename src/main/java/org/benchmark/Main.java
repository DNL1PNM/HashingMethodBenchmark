package org.benchmark;

public class Main {

    public static void main(String[] args) {
        HashTableBenchmark<String, Integer> benchmark = new HashTableBenchmark<>();
        benchmark.runBenchmark();
    }
}