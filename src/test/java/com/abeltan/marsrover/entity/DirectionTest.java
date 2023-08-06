package com.abeltan.marsrover.entity;

import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;

import static com.abeltan.marsrover.entity.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void testThatParseDirectionParsesDirectionCorrectly() {
        assertEquals(NORTH, parseDirection("N"));
        assertEquals(NORTH, parseDirection("n"));
        assertEquals(SOUTH, parseDirection("S"));
        assertEquals(SOUTH, parseDirection("s"));
        assertEquals(EAST, parseDirection("E"));
        assertEquals(EAST, parseDirection("e"));
        assertEquals(WEST, parseDirection("W"));
        assertEquals(WEST, parseDirection("w"));
        assertEquals(NONE, parseDirection("gibberish"));
    }

}