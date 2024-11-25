package com.parkinglot.exception;

import static com.parkinglot.common.constant.NO_AVAILABLE_POSITION;

public class NoAvailablePositionException extends RuntimeException {

    public NoAvailablePositionException() {
        super(NO_AVAILABLE_POSITION);
    }
}
