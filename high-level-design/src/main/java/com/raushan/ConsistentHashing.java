package com.raushan;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ConsistentHashing {
    private final int numberOfReplicas; // No. of VN per server
    private final TreeMap<Long, String> ring; // Hash ring storing VN
    private final Set<String> servers; // Set of physical servers

    public ConsistentHashing(int numberOfReplicas, List<String> servers) {
        this.numberOfReplicas = numberOfReplicas;
        this.servers = new HashSet<>();

        for (String server : servers) {
            addServer(server);
        }
    }

    private long hash(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(key.getBytes());
            byte[] digest = md.digest();
            return ((long) (digest[0] & 0xFF) << 24) |
                    ((long) (digest[1] & 0xFF) << 16) |
                    ((long) (digest[2] & 0xFF) << 8) |
                    (digest[3] & 0xFF);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not supported");
        }
    }

    public void addServer(String server) {
        servers.add(server);
        for (int i = 0; i < numberOfReplicas; i++) {
            long hash = hash(server + "-" + i);
            ring.put(hash, server);
        }
    }

    public void removeServer(String server) {
        if (servers.remove(server)) {
            for (int i = 0; i < numberOfReplicas; i++) {
                long hash = hash(server + "-" + i);
                ring.remove(hash);
            }
        }
    }

    public String getServer(String key) {
        if (ring.isEmpty()) {
            return null;
        }

        long hash = hash(key);
        // Find the closest node in the hash ring
        Map.Entry<Long, String> closest = ring.ceilingEntry(hash);
        if (closest == null) {
            closest = ring.firstEntry();
        }
        return closest.getValue();
    }


}


public class Main {
    public static void main(String[] args) {
        List<String> servers = Arrays.asList("S0", "S1", "S2", "S3", "S4", "S5");
        var ch = new ConsistentHashing(3, servers);
        // Step 2: Assign requests (keys) to servers
        System.out.println("UserA is assigned to: " + ch.getServer("UserA"));
        System.out.println("UserB is assigned to: " + ch.getServer("UserB"));

        // Step 3: Add a new server dynamically
        ch.addServer("S6");
        System.out.println("UserA is now assigned to: " + ch.getServer("UserA"));

        // Step 4: Remove a server dynamically
        ch.removeServer("S2");
        System.out.println("UserB is now assigned to: " + ch.getServer("UserB"));
    }
}