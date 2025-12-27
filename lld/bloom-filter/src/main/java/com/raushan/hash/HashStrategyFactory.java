package com.raushan.hash;

public class HashStrategyFactory {
    public static HashStrategy create(HashType hashType) {
        return switch (hashType) {
            case FNV1A -> new FNV1aHashStrategy();
            case DJB2 -> new DJB2HashStrategy();
            default -> throw new IllegalArgumentException("Invalid hash type");
        };
    }
}
