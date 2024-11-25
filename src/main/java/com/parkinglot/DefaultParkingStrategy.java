package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;

import java.util.List;

public class DefaultParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLot selectParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailablePosition)
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new);
    }
}
