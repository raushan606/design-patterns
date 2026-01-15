package com.raushan.locker;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private final Compartment[] compartments;
    private Map<String, AccessToken> activeTokens;

    public Locker(Compartment[] compartments) {
        this.compartments = compartments;
        this.activeTokens = new HashMap<>();
    }

    public AccessToken depositPackage(Size packageSize) {
        if (packageSize == null) {
            throw new IllegalArgumentException("Package size cannot be null");
        }

        // Find an available compartment of the required size
        var compartment = findAvailableCompartment(packageSize);
        if (compartment == null) {
            System.out.println("No available compartment for size: " + packageSize);
            return null;
        }
        compartment.open();

        // Mark the compartment as occupied
        compartment.markOccupied(true);
        // Generate an access token for the compartment
        var accessToken = new AccessToken(compartment);
        // Store the active token
        activeTokens.put(accessToken.getToken(), accessToken);
        System.out.println("Package deposited in compartment of size: " + packageSize);
        compartment.close();
        return accessToken;
    }

    public void pickup(String accessCode) {
        if (accessCode == null || accessCode.isBlank()) {
            throw new IllegalArgumentException("Access code cannot be null or blank");
        }

        var accessToken = activeTokens.get(accessCode);
        if (accessToken.isValid(accessCode)) {
            var compartment = accessToken.getCompartment();
            compartment.open();
            System.out.println("Package picked up from compartment of size: " + compartment.getSize());
            clearDeposit(accessToken);
            compartment.close();
        } else {
            System.out.println("Invalid or expired access code: " + accessCode);
        }
    }

    public void openExpiredCompartments() {
        for (AccessToken accessToken : activeTokens.values()) {
            if (accessToken.isExpired()) {
                Compartment compartment = accessToken.getCompartment();
                compartment.open();
            }
        }
    }

    private void clearDeposit(AccessToken accessToken) {
        Compartment compartment = accessToken.getCompartment();
        compartment.markVacant();
        activeTokens.remove(accessToken.getToken());
    }

    private Compartment findAvailableCompartment(Size packageSize) {
        var index = -1;
        for (int i = 0; i < Size.values().length; i++) {
            if (Size.values()[i] == packageSize) {
                index = i;
                break;
            }
        }
        for (int i = index; i < Size.values().length; i++) {
            for (Compartment compartment : compartments) {
                if (compartment.getSize() == packageSize && !compartment.isOccupied()) {
                    return compartment;
                }
            }
        }
        return null;
    }

}
