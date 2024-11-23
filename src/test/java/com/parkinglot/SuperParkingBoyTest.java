package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperParkingBoyTest {

    @Test
    void should_park_in_first_parking_lot_when_both_have_same_available_position_rate() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SuperParkingBoy superSmartParkingBoy = new SuperParkingBoy(Arrays.asList(parkingLot1, parkingLot2));

        // When
        Car car = new Car();
        Ticket ticket = superSmartParkingBoy.park(car);

        // Then
        assertEquals(9, parkingLot1.getRemainingCapacity());
        assertEquals(10, parkingLot2.getRemainingCapacity());
        assertEquals(car, parkingLot1.fetch(ticket));
    }


}
