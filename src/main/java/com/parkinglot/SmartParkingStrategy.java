package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot selectParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailablePosition)
                .max(Comparator.comparingInt(ParkingLot::getRemainingCapacity))
                .orElseThrow(NoAvailablePositionException::new);
    }
}
