package com.abeltan.marsrover.action;

public interface Command {
    void undo();
    void execute();
}
