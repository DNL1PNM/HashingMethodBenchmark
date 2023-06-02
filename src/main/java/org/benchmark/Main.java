package org.benchmark;

public class Main {

    public static void main(String[] args) {
       PseudoBenchmark<String, Integer> pseudoBenchmark = new PseudoBenchmark<>();
        ChainingBenchmark<String, Integer> chainingBenchmark = new ChainingBenchmark<>();
        LinearProbingBenchmark<String, Integer> linearProbingBenchmark = new LinearProbingBenchmark<>();


         pseudoBenchmark.run();
          chainingBenchmark.run();
       linearProbingBenchmark.run();
    }
}