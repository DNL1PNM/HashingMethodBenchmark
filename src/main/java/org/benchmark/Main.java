package org.benchmark;

public class Main {

    public static void main(String[] args) {
        System.out.println("Pseudo");
        PseudoBenchmark<String, Integer> pseudoBenchmark = new PseudoBenchmark<>();
        pseudoBenchmark.run();
        System.out.println();
        System.out.println("Chaining");
        ChainingBenchmark<String, Integer> chainingBenchmark = new ChainingBenchmark();
        chainingBenchmark.run();
        System.out.println("Linear Probing");
        LinearProbingBenchmark<String, Integer> linearProbingBenchmark = new LinearProbingBenchmark<>();
        linearProbingBenchmark.run();
    }
}