package org.example.command_support;

import lombok.Getter;
import org.example.command.Command;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Класс ответственный за взаимодействие с историей команд и скриптов
 *
 */

@Getter
public class History {
    private final int maxHistorySize;
    private final List<Command> commandHistory;
    private final LinkedList<String> workingScripts;

    private static History instance;

    public static History getInstance() {
        if (instance == null) {
            instance = new History();
        }

        return instance;
    }

    private History() {
        maxHistorySize = 14;
        commandHistory = new LinkedList<>();
        workingScripts = new LinkedList<>();
    }

    public boolean isScriptWorking(String name) {
        return workingScripts.contains(name);
    }

    public String getPreviousScript() {
        workingScripts.pop();

        if (workingScripts.isEmpty()) {
            return null;
        }

        return workingScripts.getLast();
    }

    public void addScript(String name) {
        workingScripts.add(name);
    }

    public void add(Command command) {
        if (commandHistory.size() > maxHistorySize) {
            commandHistory.remove(0);
        }

        commandHistory.add(command);
    }
}
