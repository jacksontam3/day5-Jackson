package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<Ticket, Car> ticketToCar = new HashMap<>();

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        if (ticketToCar.size() == 10) return null;
        ticketToCar.put(ticket, car);
        return ticket;
    }

    public void validateTicket(Ticket ticket) throws Exception {
        if (ticket == null) {
            throw new UnrecognizedParkingTickerException();
        }
        if (ticket.isUsed()) {
            throw new UnrecognizedParkingTickerException();
        }
        if (!ticketToCar.containsKey(ticket)) {
            throw new UnrecognizedParkingTickerException();
        }
    }

    public Car fetch(Ticket ticket) throws Exception {
        validateTicket(ticket);
        ticket.setUsed(true);
        return ticketToCar.get(ticket);
    }

    public Map<Ticket, Car> getTicketToCar() {
        return ticketToCar;
    }

    public void setTicketToCar(Map<Ticket, Car> ticketToCar) {
        this.ticketToCar = ticketToCar;
    }
}
