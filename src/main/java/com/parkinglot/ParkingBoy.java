package com.parkinglot;

public class ParkingBoy {

    private ParkingLot parkingLot = new ParkingLot();

    public Ticket park(Car car){
        return parkingLot.park(car);
    }
}
