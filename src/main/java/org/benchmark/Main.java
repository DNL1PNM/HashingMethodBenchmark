package org.benchmark;

public class Main {

    public static void main(String[] args) {
        PseudoBenchmark<String, Integer> pseudoBenchmark = new PseudoBenchmark<>();
        pseudoBenchmark.run();
//        ChainingBenchmark<String, Integer> chainingBenchmark = new ChainingBenchmark<>();
//        chainingBenchmark.run();
//
//        LinearProbingBenchmark<String, Integer> linearProbingBenchmark = new LinearProbingBenchmark<>();
//        linearProbingBenchmark.run();
    }
}