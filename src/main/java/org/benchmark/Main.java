package org.benchmark;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Benchmark<String, Integer> chainingBenchmark = new Benchmark<>();
        Benchmark<String, Integer> linearBenchmark = new Benchmark<>();
        Benchmark<String, Integer> doubleBenchmark = new Benchmark<>();

        HashFunc<String> sha256 = new CustomSHA256<>();
        HashFunc<String> md5 = new CustomMD5<>();
        HashFunc<String> crc32 = new CustomCRC32<>();

        System.out.println("Введите необходимое количество операций для Chaining Method" );
        int chainingVolume = scanner.nextInt();
        System.out.println("Введите необходимое количество операций для Linear Method" );
        int linearVolume = scanner.nextInt();
        System.out.println("Введите необходимое количество операций для Pseudo Method" );
        int doubleVolume = scanner.nextInt();

        HashTable<String, Integer> ChainingSHA256 = new ChainingHash<>(sha256, chainingVolume);
        HashTable<String, Integer> ChainingMD5 = new ChainingHash<>(md5, chainingVolume);
        HashTable<String, Integer> ChainingCRC32 = new ChainingHash<>(crc32, chainingVolume);

        HashTable<String, Integer> LinearSHA256 = new LinearHash<>(sha256,linearVolume);
        HashTable<String, Integer> LinearMD5 = new LinearHash<>(md5,linearVolume);
        HashTable<String, Integer> LinearCRC32 = new LinearHash<>(crc32,linearVolume);

        HashTable<String, Integer> DoubleSHA256 = new DoubleHash<>(sha256,doubleVolume);
        HashTable<String, Integer> DoubleMD5 = new DoubleHash<>(md5,doubleVolume);
        HashTable<String, Integer> DoubleCRC32 = new DoubleHash<>(crc32,doubleVolume);

        System.out.println("1) Method = " + ChainingHash.class +
                "(" + chainingVolume + "/" + chainingBenchmark.getSize() + ")" );
        chainingBenchmark.run(ChainingSHA256, chainingVolume);
        chainingBenchmark.run(ChainingMD5, chainingVolume);
        chainingBenchmark.run(ChainingCRC32, chainingVolume);

        System.out.println("2) Method = " + LinearHash.class +
                "(" + linearVolume +  "/" + linearBenchmark.getSize() + ")" );
        linearBenchmark.run(LinearSHA256,linearVolume);
        linearBenchmark.run(LinearMD5,linearVolume);
        linearBenchmark.run(LinearCRC32,linearVolume);

        System.out.println("3) Method = " + DoubleHash.class +
                "(" + doubleVolume + "/" + doubleBenchmark.getSize() + ")" );
        doubleBenchmark.run(DoubleSHA256,doubleVolume);
        doubleBenchmark.run(DoubleMD5,doubleVolume);
        doubleBenchmark.run(DoubleCRC32,doubleVolume);

    }
}