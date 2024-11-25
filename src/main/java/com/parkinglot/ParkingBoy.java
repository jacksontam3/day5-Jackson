package com.parkinglot;

import com.parkinglot.exception.UnrecognizedParkingTickerException;

import java.util.List;

import static com.parkinglot.common.constant.PARKING_LOTS_CANNOT_BE_NULL_OR_EMPTY;

public class ParkingBoy {

    protected List<ParkingLot> parkingLots;
    private final ParkingStrategy parkingStrategy;

    public ParkingBoy(List<ParkingLot> parkingLots, ParkingStrategy parkingStrategy) {
        if (parkingLots == null || parkingLots.isEmpty()) {
            throw new IllegalArgumentException(PARKING_LOTS_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.parkingLots = parkingLots;
        this.parkingStrategy = parkingStrategy;
    }

    public Ticket park(Car car) {
        ParkingLot selectedParkingLot = parkingStrategy.selectParkingLot(parkingLots);
        return selectedParkingLot.park(car);
    }

    public Car fetch(Ticket ticket) throws Exception {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.hasTicket(ticket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTickerException::new)
                .fetch(ticket);
    }
}