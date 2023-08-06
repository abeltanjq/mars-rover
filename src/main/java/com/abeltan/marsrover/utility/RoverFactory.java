package com.abeltan.marsrover.utility;

import com.abeltan.marsrover.entity.Coordinate;
import com.abeltan.marsrover.entity.Direction;
import com.abeltan.marsrover.entity.Rover;
import com.abeltan.marsrover.exception.InvalidRoverConfiguration;

public class RoverFactory {
    /**
     * Retuns a Rover object.
     * @param coordinateDirection valid format: int,int,("N"|"n"|"S"|"s"|"E"|"e"|"W"|"w") eg. 1,2,N
     * @return a Rover initialized to the given configuration
     */
    public static Rover create(String coordinateDirection) throws InvalidRoverConfiguration {
        String[] cd = coordinateDirection.split(",");
        if (cd.length != 3) {
            throw new InvalidRoverConfiguration();
        }

        Direction direction = Direction.parseDirection(cd[2]);
        if (direction == Direction.NONE) {
            throw new InvalidRoverConfiguration();
        }

        try {
            Coordinate coordinate = new Coordinate(Integer.parseInt(cd[0]), Integer.parseInt(cd[1]));
            return new Rover(coordinate, direction);
        } catch (Exception e) {
            throw new InvalidRoverConfiguration();
        }
    }
}
