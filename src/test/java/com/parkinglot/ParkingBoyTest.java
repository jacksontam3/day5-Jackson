package com.parkinglot;

import org.junit.jupiter.api.Test;

import static com.parkinglot.ParkingLotTest.NO_AVAILABLE_POSITION;
import static com.parkinglot.ParkingLotTest.UNRECOGNIZED_PARKING_TICKET;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    @Test
    void should_return_ticket_when_park_given_a_car_and_a_parking_lot(){
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetch_a_car_given_a_parking_lot() throws Exception {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        //When
        Car fetchedCar = parkingBoy.fetch(ticket);
        //Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_two_cars_when_fetch_given_a_parking_lot() throws Exception {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        Car car2 = new Car();
        Ticket ticket2 = parkingBoy.park(car2);
        //When
        Car fetchedCar = parkingBoy.fetch(ticket);
        Car fetchedCar2 = parkingBoy.fetch(ticket2);
        //Then
        assertEquals(car, fetchedCar);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_error_message_when_fetch_given_wrong_parking_ticket() throws Exception {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        Ticket ticket1 = new Ticket();
        //When
        Exception exception = assertThrows(UnrecognizedParkingTickerException.class, () -> parkingBoy.fetch(ticket1));
        //Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, exception.getMessage());
    }

    @Test
    void should_return_nothing_when_fetch_given_used_parking_ticket() throws Exception {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(ticket);
        //When
        Exception exception = assertThrows(UnrecognizedParkingTickerException.class, () -> parkingBoy.fetch(ticket));
        //Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, exception.getMessage());
    }

    @Test
    void should_return_nothing_when_fetch_given_full_parking_slot() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        Ticket ticket1 = parkingBoy.park(car);
        Ticket ticket2 = parkingBoy.park(car);
        Ticket ticket3 = parkingBoy.park(car);
        Ticket ticket4 = parkingBoy.park(car);
        Ticket ticket5 = parkingBoy.park(car);
        Ticket ticket6 = parkingBoy.park(car);
        Ticket ticket7 = parkingBoy.park(car);
        Ticket ticket8 = parkingBoy.park(car);
        Ticket ticket9 = parkingBoy.park(car);
//When
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(car));
        //Then
        assertEquals(NO_AVAILABLE_POSITION, exception.getMessage());
    }


}
