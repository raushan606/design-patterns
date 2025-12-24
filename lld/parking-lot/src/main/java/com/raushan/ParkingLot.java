package com.raushan;

import com.raushan.entities.ParkingFloor;
import com.raushan.entities.ParkingSpot;
import com.raushan.entities.ParkingTicket;
import com.raushan.strategy.fee.FeeStrategy;
import com.raushan.strategy.fee.FlatRateFeeStrategy;
import com.raushan.strategy.parking.BestFitStrategy;
import com.raushan.strategy.parking.NearestFirstStrategy;
import com.raushan.strategy.parking.ParkingStrategy;
import com.raushan.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot instance;
    private final List<ParkingFloor> floors = new ArrayList<>();
    private final Map<String, ParkingTicket> activeTickets;
    private FeeStrategy feeStrategy;
    private ParkingStrategy parkingStrategy;


    private ParkingLot() {
        this.feeStrategy = new FlatRateFeeStrategy();
        this.parkingStrategy = new BestFitStrategy();
        this.activeTickets = new ConcurrentHashMap<>();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> spot = parkingStrategy.findSpot(floors, vehicle);
        if (spot.isPresent()) {
            ParkingSpot parkingSpot = spot.get();
            parkingSpot.parkVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(vehicle, parkingSpot);
            activeTickets.put(ticket.getTicketId(), ticket);
            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getLicensePlate(), spot.get().getSpotId(), ticket.getTicketId());
            return Optional.of(ticket);
        }
        System.out.printf("No available spot for vehicle %s", vehicle.getLicensePlate());
        return Optional.empty();
    }

    public Optional<Double> unparkVehicle(String licensePlate) {
        ParkingTicket ticket = activeTickets.get(licensePlate);
        if (ticket == null) {
            System.out.println("Ticket not found");
            return Optional.empty();
        }

        ticket.setExitTimestamp();
        ticket.getSpot().unparkVehicle();

        Double parkingFee = feeStrategy.calculateFee(ticket);

        return Optional.of(parkingFee);
    }
}
