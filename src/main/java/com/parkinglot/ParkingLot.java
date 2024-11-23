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
        return getRemainingCapacity() > 0;
    }

    public double getAvailablePositionRate() {
        if (capacity == 0) {
            return 0;
        }
        return (double) getRemainingCapacity() / capacity;
    }

    public Car fetch(Ticket ticket) throws Exception {
        validateTicket(ticket);
        ticket.setUsed(true);
        return ticketToCar.remove(ticket);
    }

    public int getRemainingCapacity() {
        return capacity - ticketToCar.size();
    }

    public boolean hasTicket(Ticket ticket) {
        return ticketToCar.containsKey(ticket);
    }

    public void validateTicket(Ticket ticket) throws Exception {
        if (ticket == null || ticket.isUsed() || !hasTicket(ticket)) {
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