package com.parkinglot;

import com.parkinglot.Exception.NoAvailablePositionException;
import com.parkinglot.Exception.UnrecognizedParkingTickerException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.parkinglot.ParkingLotTest.UNRECOGNIZED_PARKING_TICKET;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    @Test
    void should_return_ticket_when_park_given_a_car_and_a_parking_lot(){
        //Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new DefaultParkingStrategy());
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetch_a_car_given_a_parking_lot() throws Exception {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new DefaultParkingStrategy());
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
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new DefaultParkingStrategy());
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
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new DefaultParkingStrategy());
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
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new DefaultParkingStrategy());
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(ticket);
        //When
        Exception exception = assertThrows(UnrecognizedParkingTickerException.class, () -> parkingBoy.fetch(ticket));
        //Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, exception.getMessage());
    }

    @Test
    void should_throw_exception_when_park_given_full_parking_lots_with_capacity_10() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new DefaultParkingStrategy());

        for (int i = 0; i < 20; i++) {
            parkingBoy.park(new Car());
        }

        // When & Then
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(new Car()));
        assertEquals("No available position", exception.getMessage());
    }

    @Test
    void should_park_in_second_parking_lot_when_first_is_full() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new DefaultParkingStrategy());

        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }

        //When
        Car carInSecondLot = new Car();
        Ticket ticketForSecondLot = parkingBoy.park(carInSecondLot);

        // Then
        assertNotNull(ticketForSecondLot);
        assertEquals(carInSecondLot, parkingBoy.fetch(ticketForSecondLot));
    }

    @Test
    void should_park_in_parking_lot_with_most_available_positions_when_using_smart_strategy() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SmartParkingStrategy());

        // When
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);

        // Then
        assertEquals(9, parkingLot1.getRemainingCapacity());
        assertEquals(10, parkingLot2.getRemainingCapacity());
    }

    @Test
    void should_park_in_parking_lot_with_largest_available_position_rate_when_using_super_smart_strategy() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SuperParkingStrategy());

        // Park a car to parking lot 1 first
        parkingLot1.park(new Car());

        // When
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);

        // Then
        assertEquals(9, parkingLot2.getRemainingCapacity());
        assertEquals(9, parkingLot1.getRemainingCapacity());

    }




}
