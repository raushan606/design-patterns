package com.raushan.strategy.parking;

import com.raushan.entities.ParkingFloor;
import com.raushan.entities.ParkingSpot;
import com.raushan.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class BestFitStrategy implements ParkingStrategy {

    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        Optional<ParkingSpot> bestSpot = Optional.empty();

        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spotOnThisFloor = floor.findAvailableSpot(vehicle);

            if (spotOnThisFloor.isPresent()) {
                if (bestSpot.isEmpty()) {
                    // If this is the first spot we've found, it's the best one so far.
                    bestSpot = spotOnThisFloor;
                } else {
                    // A smaller spot size enum ordinal means a tighter fit.
                    if (spotOnThisFloor.get().getspotSize().ordinal() < bestSpot.get().getspotSize().ordinal()) {
                        bestSpot = spotOnThisFloor;
                    }
                }
            }
        }
        return bestSpot;
    }
}
