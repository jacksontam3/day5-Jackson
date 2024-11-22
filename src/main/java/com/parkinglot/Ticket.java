package com.parkinglot;

import java.util.UUID;

public class Ticket {
    private String uniqueID;

    public Ticket() {
        String uniqueID = UUID.randomUUID().toString();;
    }
}
