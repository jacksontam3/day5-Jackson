package com.parkinglot;

public class UnrecognizedParkingTickerException extends RuntimeException {
  public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket";
    public UnrecognizedParkingTickerException() {
        super(UNRECOGNIZED_PARKING_TICKET);
    }
}
