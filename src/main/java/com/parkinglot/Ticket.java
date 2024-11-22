package com.parkinglot;

import java.util.UUID;

public class Ticket {
    private String uniqueID;

    private boolean isUsed;

    public Ticket() {
        String uniqueID = UUID.randomUUID().toString();;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
