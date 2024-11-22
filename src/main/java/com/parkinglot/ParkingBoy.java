package com.parkinglot;

public class ParkingBoy {

    private ParkingLot parkingLot = new ParkingLot();

    public Ticket park(Car car){
        return parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) throws Exception {
        return parkingLot.fetch(ticket);
    }
}
