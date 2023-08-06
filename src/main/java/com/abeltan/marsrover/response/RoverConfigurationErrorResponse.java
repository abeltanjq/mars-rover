package com.abeltan.marsrover.response;

public class RoverConfigurationErrorResponse {
    private int status;
    private String message;

    public RoverConfigurationErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
