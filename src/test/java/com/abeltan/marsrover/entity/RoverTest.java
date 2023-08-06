package com.abeltan.marsrover.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {
    @Test
    void testThatRoverIdIsSetCorrectly() {
        Rover r = new Rover(new Coordinate(1,2), Direction.NORTH);

        assertEquals("", r.getId());

        r.setId(22);
        assertEquals("R22", r.getId());
    }
}