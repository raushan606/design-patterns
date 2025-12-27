package com.raushan.filter;

import com.raushan.hash.HashStrategy;

import java.util.BitSet;
import java.util.List;

public class BloomFilter {
    private final BitSet bitSet;
    private final int size;
    private final int numHashFunctions;
    private final List<HashStrategy> hashStrategies;

    private BloomFilter(int size, int numHashFunctions, List<HashStrategy> hashStrategies) {
        this.size = size;
        this.numHashFunctions = numHashFunctions;
        this.hashStrategies = hashStrategies;
        bitSet = new BitSet(size);
    }

    public void add(String data) {
        for (HashStrategy hashStrategy : hashStrategies) {
            long hash = hashStrategy.hash(data);
            int index = (int) (Math.abs(hash) % size);
            bitSet.set(index);
        }
    }

    public boolean mightContain(String key) {
        for (HashStrategy hashStrategy : hashStrategies) {
            long hash = hashStrategy.hash(key);
            int index = (int) (Math.abs(hash) % size);
            if (!bitSet.get(index)) {
                return false;
            }
        }
        return true;
    }

    public static class Builder {
        private int size;
        private int numHashFunctions;
        private List<HashStrategy> hashStrategies;

        public Builder withBitSetSize(int size) {
            if (size <= 0) {
                throw new IllegalArgumentException("Size should be greater than 0");
            }
            this.size = size;
            return this;
        }

        public Builder withNumHashFunctions(int numHashFunctions) {
            if (numHashFunctions <= 0) {
                throw new IllegalArgumentException("Number of hash functions should be greater than 0");
            }
            this.numHashFunctions = numHashFunctions;
            return this;
        }

        public Builder withHashStrategies(List<HashStrategy> hashStrategies) {
            if (hashStrategies == null || hashStrategies.isEmpty()) {
                throw new IllegalArgumentException("Hash strategies cannot be null or empty");
            }
            this.hashStrategies = hashStrategies;
            return this;
        }

        public BloomFilter build() {
            if (size == 0 || numHashFunctions == 0 || hashStrategies == null) {
                throw new IllegalStateException("Required fields are not set.");
            }
            if (hashStrategies.size() < numHashFunctions) {
                throw new IllegalStateException("Number of hash functions cannot be greater than number of hash strategies.");
            }
            System.out.println("Creating Bloom Filter with size: " + size + ", numHashFunctions: " + numHashFunctions + ", hashStrategies:");
            return new BloomFilter(size, numHashFunctions, hashStrategies);
        }
    }
}
