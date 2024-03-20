package org.example.command_support;

import lombok.Getter;
import org.example.command.Command;
import org.example.model.LabWork;
import org.example.repository.LabWorkRepositoryImpl;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

@Getter
public class CommandHistory {
    private final int maxHistorySize;
    private final List<Command> commandHistory;

    private static CommandHistory instance;

    public static CommandHistory getInstance(){
        if (instance == null){
            instance = new CommandHistory();
        }

        return instance;
    }

    private CommandHistory(){
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
