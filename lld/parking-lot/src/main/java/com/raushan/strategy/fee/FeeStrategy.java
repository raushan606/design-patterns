package com.raushan.strategy.fee;

import com.raushan.entities.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket ticket);
}
