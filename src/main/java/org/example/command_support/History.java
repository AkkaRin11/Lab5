package org.example.command_support;

import lombok.Getter;
import org.example.command.Command;

import java.util.*;

@Getter
public class History {
    private final int maxHistorySize;
    private final List<Command> commandHistory;
    private final Set<String> workingScripts;

    private static History instance;

    public static History getInstance(){
        if (instance == null){
            instance = new History();
        }

        return instance;
    }

    private History(){
        maxHistorySize = 14;
        commandHistory = new LinkedList<>();
        workingScripts = new HashSet<>();
    }

    public boolean isScriptWorking(String name){
        return workingScripts.contains(name);
    }

    public void addScript(String name){
        workingScripts.add(name);
    }

    public void Add(Command command){
        if (commandHistory.size() > maxHistorySize){
            commandHistory.remove(0);
        }

        commandHistory.add(command);
    }
}
