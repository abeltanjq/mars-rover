package com.abeltan.marsrover.service;

import com.abeltan.marsrover.entity.Direction;
import com.abeltan.marsrover.entity.Rover;
import com.abeltan.marsrover.exception.InvalidRoverConfiguration;
import com.abeltan.marsrover.exception.RoverNotFoundException;
import com.abeltan.marsrover.utility.RoverFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoverServiceImplTest {
    @Autowired
    private RoverService rs;

    @Test
    @Order(1)
    void ableToAddRover() {
        Rover r = RoverFactory.create("0,0,N");
        assertEquals(r, rs.add(r));
    }

    @Test
    @Order(2)
    void unableToAddRoverAtTheSameSpot() {
        assertThrowsExactly(InvalidRoverConfiguration.class, () -> {
           rs.add(RoverFactory.create("0,0,S"));
        });
    }

    @Test
    @Order(2)
    void ableToFindExistingRover() {
        assertNotNull(rs.find("R1"));
    }

    @Test
    @Order(2)
    void unableToFindNonExistingRover() {
        assertNull(rs.find("R99"));
    }

    @Test
    @Order(3)
    void ableToFindAllRovers() {
        rs.add(RoverFactory.create("1,2,W"));
        assertEquals(2, rs.findAll().size());
    }

    @Test
    void unableToMoveNonExistingRover() {
        assertThrowsExactly(RoverNotFoundException.class, () -> {
            rs.move("R55", "f,f,f,f,f,1");
        });
    }

    @Test
    @Order(4)
    void ableToMoveExistingRover() {
        rs.move("R2", "f,b,f,r,r,r");
        Rover r = rs.find("R2");
        assertEquals(0, r.getCoordinate().getX());
        assertEquals(2, r.getCoordinate().getY());
        assertEquals(Direction.SOUTH, r.getDirection());
    }

    @Test
    @Order(5)
    void stopAtPositionWhenEncounteringAnotherRover() {
        rs.move("R1", "f,f,f,f,B,l");
        Rover r = rs.find("R1");
        assertEquals(0, r.getCoordinate().getX());
        assertEquals(1, r.getCoordinate().getY());
        assertEquals(Direction.NORTH, r.getDirection());
    }
 }
