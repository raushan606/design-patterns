package com.raushan.strategy.parking;

import com.raushan.entities.ParkingFloor;
import com.raushan.entities.ParkingSpot;
import com.raushan.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}
