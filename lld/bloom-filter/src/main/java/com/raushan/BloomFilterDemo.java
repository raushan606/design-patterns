package com.raushan;

import com.raushan.filter.BloomFilter;
import com.raushan.hash.HashStrategy;
import com.raushan.hash.HashStrategyFactory;
import com.raushan.hash.HashType;

import java.util.List;
import java.util.UUID;

public class BloomFilterDemo {
    public static void main(String[] args) {
        System.out.println("Bloom Filter Demo");
        int bitSetSize = 10000;
        int numHashFunctions = 2;
        int expectedInsertions = 1000;

        List<HashStrategy> strategies = List.of(
                HashStrategyFactory.create(HashType.FNV1A),
                HashStrategyFactory.create(HashType.DJB2)
        );

        BloomFilter filter = new BloomFilter.Builder()
                .withBitSetSize(bitSetSize)
                .withNumHashFunctions(numHashFunctions)
                .withHashStrategies(strategies)
                .build();

        System.out.println("---Adding Elements ----");
        List<String> ele = List.of("Alice", "Bob", "Charlie", "David");
        ele.forEach(filter::add);

        System.out.println("---Checking Elements ----");
        boolean hasFalseNegative = false;
        for (String e : ele) {
            if (!filter.mightContain(e)) {
                System.out.println("False Negative for " + e);
                hasFalseNegative = true;
                break;
            }
        }

        if (!hasFalseNegative) {
            System.out.println("SUCCESS!");
        }

        System.out.println("\n--- Testing for false positives ---");
        int testSetSize = 10000;
        int falsePositivesCount = 0;
        for (int i = 0; i < testSetSize; i++) {
            String randomElement = UUID.randomUUID().toString();
            if (filter.mightContain(randomElement)) {
                falsePositivesCount++;
            }
        }
        System.out.println("Number of false positives found: " + falsePositivesCount + " out of " + testSetSize + " random items.");

    }
}