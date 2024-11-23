package com.parkinglot;

import java.util.List;

public class SmartParkingBoy {

    private List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        if (parkingLots == null || parkingLots.isEmpty()) {
            throw new IllegalArgumentException("Parking lots cannot be null or empty.");
        }
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {

        ParkingLot targetParkingLot = parkingLots.stream()
                .filter(ParkingLot::hasAvailablePosition)
                .max((lot1, lot2) -> Integer.compare(lot1.getRemainingCapacity(), lot2.getRemainingCapacity()))
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