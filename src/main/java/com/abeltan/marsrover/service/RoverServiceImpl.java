package com.abeltan.marsrover.service;

import com.abeltan.marsrover.action.Command;
import com.abeltan.marsrover.action.CommandFactory;
import com.abeltan.marsrover.entity.Rover;
import com.abeltan.marsrover.exception.InvalidRoverConfiguration;
import com.abeltan.marsrover.exception.RoverNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoverServiceImpl implements RoverService{

    private HashMap<String, Rover> rovers;
    private Set<String> roverPositions;
    public RoverServiceImpl() {
        rovers = new HashMap<>();
        roverPositions = new HashSet<>();
    }

    @Override
    public Rover add(Rover rover) {
        if (roverPositions.contains(rover.getCoordinate().toString())) {
            throw new InvalidRoverConfiguration("Existing rover on the same coordinates.");
        }
        rover.setId(rovers.size() + 1);
        rovers.put(rover.getId(), rover);
        roverPositions.add(rover.getCoordinate().toString());
        return rover;
    }

    @Override
    public Rover find(String roverId) {
        return rovers.get(roverId);
    }

    @Override
    public List<Rover> findAll() {
        return new ArrayList<>(rovers.values());
    }

    @Override
    public Rover move(String roverId, String cmds) {
        Rover r = rovers.get(roverId);
        if (Objects.isNull(r)) {
            throw new RoverNotFoundException("Rover is not found.");
        }
        String[] commands = cmds.split(",");

        String lastPosition = r.getCoordinate().toString();
        roverPositions.remove(lastPosition);

        for (String c: commands) {
            Command command = CommandFactory.getCommand(c, r);
            command.execute();

            if (roverPositions.contains(r.getCoordinate().toString())) {
                command.undo();
                break;
            }
        }

        roverPositions.add(r.getCoordinate().toString());
        return r;
    }
}
