package com.parkinglot;

import java.util.List;

public interface ParkingStrategy {
    ParkingLot selectParkingLot(List<ParkingLot> parkingLots);
}