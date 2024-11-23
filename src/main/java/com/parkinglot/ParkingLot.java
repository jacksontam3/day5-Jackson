package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<Ticket, Car> ticketToCar = new HashMap<>();
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (ticketToCar.size() >= capacity) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket();
        ticketToCar.put(ticket, car);
        return ticket;
    }

    public boolean hasAvailablePosition() {
        return ticketToCar.size() < capacity;
    }

    public Car fetch(Ticket ticket) throws Exception {
        validateTicket(ticket);
        ticket.setUsed(true);
        return ticketToCar.remove(ticket);
    }

    public void validateTicket(Ticket ticket) throws Exception {
        if (ticket == null || ticket.isUsed() || !ticketToCar.containsKey(ticket)) {
            throw new UnrecognizedParkingTickerException();
        }
    }


    @Override
    public String toString() {
        return "ParkingLot{" +
                "capacity=" + capacity +
                ", occupiedSpaces=" + ticketToCar.size() +
                '}';
    }
}