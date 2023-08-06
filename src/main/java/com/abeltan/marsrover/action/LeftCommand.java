package com.abeltan.marsrover.action;

import com.abeltan.marsrover.entity.Coordinate;
import com.abeltan.marsrover.entity.Direction;
import com.abeltan.marsrover.entity.Rover;

import static com.abeltan.marsrover.entity.Direction.*;

public class LeftCommand implements Command {

    private final Rover rover;
    private final Coordinate coordinate;
    private final Direction direction;

    public LeftCommand(Rover r) {
        this.rover = r;
        this.coordinate = r.getCoordinate();
        this.direction = r.getDirection();
    }

    @Override
    public void undo() {
        this.rover.setCoordinate(this.coordinate);
        this.rover.setDirection(this.direction);
    }

    @Override
    public void execute() {
        Direction d = turn(this.direction);
        this.rover.setDirection(d);
    }

    private Direction turn(Direction d) {
        switch (d) {
            case NORTH -> {
                return WEST;
            }
            case EAST -> {
                return NORTH;
            }
            case SOUTH -> {
                return EAST;
            }
            case WEST -> {
                return SOUTH;
            }
            default -> {
                return NONE;
            }
        }
    }
}
