package com.raushan.entities;

import com.raushan.vehicle.Vehicle;
import com.raushan.vehicle.VehicleSize;

public class ParkingSpot {
    private final String spotId;
    private boolean isOccupied;
    private Vehicle parkedVehicle;
    private final VehicleSize spotSize;

    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public String getSpotId() {
        return spotId;

    }

    public VehicleSize getspotSize() {
        return spotSize;
    }

    public synchronized boolean isAvailable() {
        return !isOccupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public synchronized void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public synchronized void unparkVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public boolean canFit(Vehicle vehicle) {
        if (isOccupied) return false;
        return switch (vehicle.getSize()) {
            case SMALL -> spotSize == VehicleSize.SMALL;
            case REGULAR -> spotSize == VehicleSize.REGULAR
                    || spotSize == VehicleSize.LARGE;
            case LARGE -> spotSize == VehicleSize.LARGE;
        };
    }
}
