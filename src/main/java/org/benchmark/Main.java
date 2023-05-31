package org.benchmark;

public class Main {

    public static void main(String[] args) {
        PseudoBenchmark<String, Integer> pseudoBenchmark = new PseudoBenchmark<>();
        pseudoBenchmark.run();
        System.out.println();
        ChainingBenchmark<String, Integer> chainingBenchmark = new ChainingBenchmark();
        chainingBenchmark.run();
//        System.out.println("Linear Probing");
//        LinearProbingBenchmark<String, Integer> linearProbingBenchmark = new LinearProbingBenchmark<>();
//        linearProbingBenchmark.run();
    }
}