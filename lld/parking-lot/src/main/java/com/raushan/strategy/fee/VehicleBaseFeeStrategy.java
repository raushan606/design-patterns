package com.raushan.strategy.fee;

import com.raushan.entities.ParkingTicket;
import com.raushan.vehicle.VehicleSize;

import java.util.Map;

public class VehicleBaseFeeStrategy implements FeeStrategy {

    private static final Map<VehicleSize, Double> HOURLY_RATES = Map.of(
            VehicleSize.SMALL, 10.0,
            VehicleSize.REGULAR, 15.0,
            VehicleSize.LARGE, 20.0
    );

    @Override
    public double calculateFee(ParkingTicket ticket) {
        long duration = ticket.getExitTimestamp() - ticket.getEntryTimestamp();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return HOURLY_RATES.get(ticket.getVehicle().getSize()) * hours;
    }
}
