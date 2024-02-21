package org.example.command_support;

import org.example.command.Command;

import java.util.LinkedList;
import java.util.List;

public class CommandHistory {
    private final int maxHistorySize;
    private final List<Command> commandHistory;

    public CommandHistory(){
        maxHistorySize = 14;
        commandHistory = new LinkedList<>();
    }

    public void Add(Command command){
        if (commandHistory.size() > maxHistorySize){
            commandHistory.remove(0);
        }

        commandHistory.add(command);
    }
}
