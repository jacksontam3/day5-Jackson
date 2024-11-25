package com.parkinglot.exception;
import static com.parkinglot.common.constant.UNRECOGNIZED_PARKING_TICKET;

public class UnrecognizedParkingTickerException extends RuntimeException {
    public UnrecognizedParkingTickerException() {
        super(UNRECOGNIZED_PARKING_TICKET);
    }
}
