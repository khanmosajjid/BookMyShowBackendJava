package com.mosajjid.bookmyshow.exception;

public class SeatNoLongerAvailableException extends RuntimeException {
    public SeatNoLongerAvailableException(String message) {
        super(message);
    }
}
