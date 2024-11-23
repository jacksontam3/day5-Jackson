package com.parkinglot;

import java.util.List;

public class ParkingBoy {

    protected List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        if (parkingLots == null || parkingLots.isEmpty()) {
            throw new IllegalArgumentException("Parking lots cannot be null or empty.");
        }
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasAvailablePosition()) {
                return parkingLot.park(car);
            }
        }
        throw new NoAvailablePositionException();
    }

    public Car fetch(Ticket ticket) throws Exception {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.fetch(ticket);
            } catch (UnrecognizedParkingTickerException e) {

            }
        }
        throw new UnrecognizedParkingTickerException();
    }
}