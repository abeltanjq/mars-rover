package com.abeltan.marsrover.action;

import com.abeltan.marsrover.entity.Coordinate;
import com.abeltan.marsrover.entity.Direction;
import com.abeltan.marsrover.entity.Rover;

public class ForwardCommand implements Command {

    private final Rover rover;
    private final Coordinate coordinate;
    private final Direction direction;

    public ForwardCommand(Rover r) {
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
        Coordinate c = new Coordinate(this.coordinate.getX(), this.coordinate.getY());
        c.move(this.direction, 1);
        this.rover.setCoordinate(c);
    }
}
