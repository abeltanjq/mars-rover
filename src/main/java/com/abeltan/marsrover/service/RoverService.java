package com.abeltan.marsrover.service;

import com.abeltan.marsrover.action.Command;
import com.abeltan.marsrover.entity.Rover;

import java.util.List;

public interface RoverService {
    Rover add(Rover rover);
    Rover find(String roverId);

    Rover move(String roverId, String commands);

    List<Rover> findAll();
}
