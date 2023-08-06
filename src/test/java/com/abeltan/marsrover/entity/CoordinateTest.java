package com.abeltan.marsrover.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void testNorthFacingUpdateYAxisCorrectly() {
        Coordinate c = new Coordinate(0,0);

        c.move(Direction.NORTH, 1);
        assertEquals(1, c.getY());
        assertEquals(0, c.getX());

        c.move(Direction.NORTH, -2);
        assertEquals(-1, c.getY());
        assertEquals(0, c.getX());
    }

    @Test
    void testSouthFacingUpdateYAxisCorrectly() {
        Coordinate c = new Coordinate(0,0);

        c.move(Direction.SOUTH, 1);
        assertEquals(-1, c.getY());
        assertEquals(0, c.getX());

        c.move(Direction.SOUTH, -3);
        assertEquals(2, c.getY());
        assertEquals(0, c.getX());
    }

    @Test
    void testEastFacingUpdateXAxisCorrectly() {
        Coordinate c = new Coordinate(0,0);

        c.move(Direction.EAST, 1);
        assertEquals(0, c.getY());
        assertEquals(1, c.getX());

        c.move(Direction.EAST, -3);
        assertEquals(0, c.getY());
        assertEquals(-2, c.getX());
    }

    @Test
    void testWestFacingUpdateXAxisCorrectly() {
        Coordinate c = new Coordinate(0,0);

        c.move(Direction.WEST, 1);
        assertEquals(0, c.getY());
        assertEquals(-1, c.getX());

        c.move(Direction.WEST, -3);
        assertEquals(0, c.getY());
        assertEquals(2, c.getX());
    }

    @Test
    void testToString() {
        Coordinate c = new Coordinate(111,234);
        assertEquals("111,234", c.toString());
    }
}