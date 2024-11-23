package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public Ticket park(Car car) {

        ParkingLot targetParkingLot = parkingLots.stream()
                .filter(ParkingLot::hasAvailablePosition)
                .max(Comparator.comparingInt(ParkingLot::getRemainingCapacity))
                .orElseThrow(NoAvailablePositionException::new);

        return targetParkingLot.park(car);
    }

    public Car fetch(Ticket ticket) throws Exception {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasTicket(ticket)) {
                return parkingLot.fetch(ticket);
            }
        }
        throw new UnrecognizedParkingTickerException();
    }
}