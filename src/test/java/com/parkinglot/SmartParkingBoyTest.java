package com.parkinglot;

import com.parkinglot.Exception.NoAvailablePositionException;
import com.parkinglot.Exception.UnrecognizedParkingTickerException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.parkinglot.ParkingLotTest.NO_AVAILABLE_POSITION;
import static com.parkinglot.ParkingLotTest.UNRECOGNIZED_PARKING_TICKET;
import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {

    @Test
    void should_return_ticket_when_park_given_a_car_and_a_parking_lot(){
        //Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SmartParkingStrategy());
        Car car = new Car();
        //When
        Ticket ticket = smartParkingBoy.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetch_a_car_given_a_parking_lot() throws Exception {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SmartParkingStrategy());
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        //When
        Car fetchedCar = smartParkingBoy.fetch(ticket);
        //Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_two_cars_when_fetch_given_a_parking_lot() throws Exception {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SmartParkingStrategy());
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        Car car2 = new Car();
        Ticket ticket2 = smartParkingBoy.park(car2);
        //When
        Car fetchedCar = smartParkingBoy.fetch(ticket);
        Car fetchedCar2 = smartParkingBoy.fetch(ticket2);
        //Then
        assertEquals(car, fetchedCar);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_error_message_when_fetch_given_wrong_parking_ticket() throws Exception {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SmartParkingStrategy());
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        Ticket ticket1 = new Ticket();
        //When
        Exception exception = assertThrows(UnrecognizedParkingTickerException.class, () -> smartParkingBoy.fetch(ticket1));
        //Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, exception.getMessage());
    }

    @Test
    void should_return_nothing_when_fetch_given_used_parking_ticket() throws Exception {
        //Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SmartParkingStrategy());
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        Car fetchedCar = smartParkingBoy.fetch(ticket);
        //When
        Exception exception = assertThrows(UnrecognizedParkingTickerException.class, () -> smartParkingBoy.fetch(ticket));
        //Then
        assertEquals(UNRECOGNIZED_PARKING_TICKET, exception.getMessage());
    }

    @Test
    void should_throw_exception_when_park_given_full_parking_lots_with_capacity_10() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SmartParkingStrategy());

        for (int i = 0; i < 20; i++) {
            smartParkingBoy.park(new Car());
        }

        // When & Then
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> smartParkingBoy.park(new Car()));
        assertEquals(NO_AVAILABLE_POSITION, exception.getMessage());
    }

    @Test
    void should_park_in_second_parking_lot_when_first_is_full() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SmartParkingStrategy());

        for (int i = 0; i < 10; i++) {
            smartParkingBoy.park(new Car());
        }

        //When
        Car carInSecondLot = new Car();
        Ticket ticketForSecondLot = smartParkingBoy.park(carInSecondLot);

        // Then
        assertNotNull(ticketForSecondLot);
        assertEquals(carInSecondLot, smartParkingBoy.fetch(ticketForSecondLot));
    }

    @Test
    void should_park_in_parking_lot_with_more_empty_positions() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SmartParkingStrategy());

        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());

        // When
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);

        // Then
        assertEquals(9, parkingLot2.getRemainingCapacity());
        assertEquals(8, parkingLot1.getRemainingCapacity());
        assertEquals(car, smartParkingBoy.fetch(ticket));
    }

    @Test
    void should_park_in_first_parking_lot_when_both_have_equal_empty_positions() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SmartParkingStrategy());

        // When
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);

        // Then
        assertEquals(9, parkingLot1.getRemainingCapacity());
        assertEquals(10, parkingLot2.getRemainingCapacity());
        assertEquals(car, smartParkingBoy.fetch(ticket));
    }

    @Test
    void should_fetch_cars_from_both_parking_lots() throws Exception {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(parkingLot1, parkingLot2), new SmartParkingStrategy());

        // Park one car in each parking lot
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = smartParkingBoy.park(car1);
        Ticket ticket2 = smartParkingBoy.park(car2);

        // Then
        assertEquals(car1, smartParkingBoy.fetch(ticket1));
        assertEquals(car2, smartParkingBoy.fetch(ticket2));
    }


}
