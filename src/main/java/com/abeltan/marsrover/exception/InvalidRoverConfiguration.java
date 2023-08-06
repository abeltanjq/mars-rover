package com.abeltan.marsrover.exception;

public class InvalidRoverConfiguration extends RuntimeException {
    public InvalidRoverConfiguration() {
        super("Invalid rover creation configuration.");
    }

    public InvalidRoverConfiguration(String errorMessage) {
        super(errorMessage);
    }
}
