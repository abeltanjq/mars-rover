package com.abeltan.marsrover.utility;

import com.abeltan.marsrover.entity.Direction;
import com.abeltan.marsrover.entity.Rover;
import com.abeltan.marsrover.exception.InvalidRoverConfiguration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverFactoryTest {

    @Test
    void testThatConfigurationWithoutExactlyThreeCommasIsInvalid() {
        assertThrowsExactly(InvalidRoverConfiguration.class, () -> {
           RoverFactory.create("12");
        });

        assertThrowsExactly(InvalidRoverConfiguration.class, () -> {
            RoverFactory.create("1,2");
        });

        assertThrowsExactly(InvalidRoverConfiguration.class, () -> {
            RoverFactory.create("1,2,hj,hj");
        });
    }

    @Test
    void testThatConfigurationWithoutValidDirectionIsInvalid() {
        assertThrowsExactly(InvalidRoverConfiguration.class, () -> {
            RoverFactory.create("1,2,h");
        });

        assertThrowsExactly(InvalidRoverConfiguration.class, () -> {
            RoverFactory.create("1,2,234");
        });
    }

    @Test
    void testThatConfigurationWithNonIntegerCoordinatesIsInvalid() {
        assertThrowsExactly(InvalidRoverConfiguration.class, () -> {
            RoverFactory.create("a,2,N");
        });

        assertThrowsExactly(InvalidRoverConfiguration.class, () -> {
            RoverFactory.create("111,b,N");
        });
    }

    @Test
    void testThatConfigurationReturnsARoverWithCorrectInitialValues() {
        Rover r = RoverFactory.create("-1,2,N");
        assertEquals("-1,2", r.getCoordinate().toString());
        assertEquals(Direction.NORTH, r.getDirection());
    }
}