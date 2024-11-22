package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_given_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //When
        Ticket ticket = parkingLot.park(car);

        //Then
        assertNotNull(ticket);

    }

    @Test
    void should_return_the_car_when_fetch_given_a_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        //When
        Car fetchedCar = parkingLot.fetch(ticket);
        //Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_two_cars_when_fetch_given_two_tickets() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 =  parkingLot.park(car1);
        Ticket ticket2 =  parkingLot.park(car2);
        //When
        Car fetchedCar1 = parkingLot.fetch(ticket1);
        Car fetchedCar2 = parkingLot.fetch(ticket2);
        //Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }



}
