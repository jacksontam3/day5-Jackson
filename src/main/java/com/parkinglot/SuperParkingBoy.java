package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(List<ParkingLot> parkingLots, ParkingStrategy parkingStrategy) {
        super(parkingLots,parkingStrategy);
    }

    @Override
    public Ticket park(Car car) {
        ParkingLot targetParkingLot = parkingLots.stream()
                .filter(ParkingLot::hasAvailablePosition)
                .max(Comparator.comparingDouble(ParkingLot::getAvailablePositionRate))
                .orElseThrow(NoAvailablePositionException::new);
        return targetParkingLot.park(car);
    }
}