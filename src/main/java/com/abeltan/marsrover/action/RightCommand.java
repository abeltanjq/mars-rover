package com.abeltan.marsrover.action;

import com.abeltan.marsrover.action.Command;
import com.abeltan.marsrover.entity.Coordinate;
import com.abeltan.marsrover.entity.Direction;
import com.abeltan.marsrover.entity.Rover;

import static com.abeltan.marsrover.entity.Direction.*;

public class RightCommand implements Command {

    private final Rover rover;
    private final Coordinate coordinate;
    private final Direction direction;

    public RightCommand(Rover r) {
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
                return EAST;
            }
            case EAST -> {
                return SOUTH;
            }
            case SOUTH -> {
                return WEST;
            }
            case WEST -> {
                return NORTH;
            }
            default -> {
                return NONE;
            }
        }
    }
}
