package com.abeltan.marsrover.entity;

import com.abeltan.marsrover.action.Command;

public class Rover {
    private String id;
    private Coordinate coordinate;
    private Direction direction;

    public Rover(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
        this.id = "";
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getId() {
        return id;
    }

    public void setId(int id) {
        this.id = String.format("R%s", id);
    }
}
