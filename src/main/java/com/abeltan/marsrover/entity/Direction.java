package com.abeltan.marsrover.entity;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NONE;

    public static Direction parseDirection(String direction) {
        switch (direction) {
            case "N", "n" -> {
                return NORTH;
            }
            case "S", "s" -> {
                return SOUTH;
            }
            case "E", "e" -> {
                return EAST;
            }
            case "W", "w" -> {
                return WEST;
            }
        }
        return NONE;
    }
}
