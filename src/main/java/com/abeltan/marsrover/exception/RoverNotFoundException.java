package com.abeltan.marsrover.exception;

public class RoverNotFoundException extends RuntimeException {
    public RoverNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
