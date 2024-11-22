package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<Ticket, Car> ticketToCar = new HashMap<>();

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        if(ticketToCar.size()==10) return null;
        ticketToCar.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if(ticket == null) return null;
        if(ticket.isUsed()) return null;
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
