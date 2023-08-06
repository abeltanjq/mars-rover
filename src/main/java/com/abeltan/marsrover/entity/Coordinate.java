package com.abeltan.marsrover.entity;

import ch.qos.logback.core.CoreConstants;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(Direction d, int displacement) {
        switch (d) {
            case NORTH -> this.y += displacement;
            case SOUTH -> this.y -= displacement;
            case WEST -> this.x -= displacement;
            case EAST -> this.x += displacement;
        }
    }

    @Override
    public String toString() {
        return String.format("%s,%s", this.x, this.y);
    }
}
