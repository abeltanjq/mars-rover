package com.abeltan.marsrover.action;

import com.abeltan.marsrover.entity.Coordinate;
import com.abeltan.marsrover.entity.Direction;
import com.abeltan.marsrover.entity.Rover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForwardCommandTest {

    @Test
    void testThatNorthFacingRoverMovesByOneAlongPositiveYAxis() {
        Rover r = new Rover(new Coordinate(0, 0), Direction.NORTH);
        Command command = new ForwardCommand(r);
        command.execute();

        assertEquals(1, r.getCoordinate().getY());
        assertEquals(0, r.getCoordinate().getX());
        assertEquals(Direction.NORTH, r.getDirection());
    }

    @Test
    void testThatSouthFacingRoverMovesByOneAlongNegativeYAxis() {
        Rover r = new Rover(new Coordinate(0, 0), Direction.SOUTH);
        Command command = new ForwardCommand(r);
        command.execute();

        assertEquals(-1, r.getCoordinate().getY());
        assertEquals(0, r.getCoordinate().getX());
        assertEquals(Direction.SOUTH, r.getDirection());
    }

    @Test
    void testThatEastFacingRoverMovesByOneAlongPositiveXAxis() {
        Rover r = new Rover(new Coordinate(0, 0), Direction.EAST);
        Command command = new ForwardCommand(r);
        command.execute();

        assertEquals(0, r.getCoordinate().getY());
        assertEquals(1, r.getCoordinate().getX());
        assertEquals(Direction.EAST, r.getDirection());
    }

    @Test
    void testThatWestFacingRoverMovesByOneAlongNegativeXAxis() {
        Rover r = new Rover(new Coordinate(0, 0), Direction.WEST);
        Command command = new ForwardCommand(r);
        command.execute();

        assertEquals(0, r.getCoordinate().getY());
        assertEquals(-1, r.getCoordinate().getX());
        assertEquals(Direction.WEST, r.getDirection());
    }

    @Test
    void testThatNorthFacingRoverUndoCommandCorrectly() {
        Rover r = new Rover(new Coordinate(1, 1), Direction.NORTH);
        Command command = new ForwardCommand(r);
        command.execute();
        command.undo();

        assertEquals(1, r.getCoordinate().getY());
        assertEquals(1, r.getCoordinate().getX());
        assertEquals(Direction.NORTH, r.getDirection());
    }

    @Test
    void testThatSouthFacingRoverUndoCommandCorrectly() {
        Rover r = new Rover(new Coordinate(2, 2), Direction.SOUTH);
        Command command = new ForwardCommand(r);
        command.execute();
        command.undo();

        assertEquals(2, r.getCoordinate().getY());
        assertEquals(2, r.getCoordinate().getX());
        assertEquals(Direction.SOUTH, r.getDirection());
    }

    @Test
    void testThatEastFacingRoverUndoCommandCorrectly() {
        Rover r = new Rover(new Coordinate(3, 3), Direction.EAST);
        Command command = new ForwardCommand(r);
        command.execute();
        command.undo();

        assertEquals(3, r.getCoordinate().getY());
        assertEquals(3, r.getCoordinate().getX());
        assertEquals(Direction.EAST, r.getDirection());
    }

    @Test
    void testThatWestFacingRoverUndoCommandCorrectly() {
        Rover r = new Rover(new Coordinate(4, 4), Direction.WEST);
        Command command = new ForwardCommand(r);
        command.execute();
        command.undo();

        assertEquals(4, r.getCoordinate().getY());
        assertEquals(4, r.getCoordinate().getX());
        assertEquals(Direction.WEST, r.getDirection());
    }
}