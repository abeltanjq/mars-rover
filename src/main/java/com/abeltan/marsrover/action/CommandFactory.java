package com.abeltan.marsrover.action;

import com.abeltan.marsrover.entity.Rover;

public class CommandFactory {

    public static Command getCommand(String command, Rover r) {
        switch (command) {
            case "F", "f" -> {
                return new ForwardCommand(r);
            }
            case "B", "b" -> {
                return new BackCommand(r);
            }
            case "R", "r" -> {
                return new RightCommand(r);
            }
            case "L", "l" -> {
                return new LeftCommand(r);
            }
        }
        return new EmptyCommand();
    }
}
