package org.example.controller;

import lombok.Getter;
import org.example.command.*;
import org.example.command_support.History;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * Класс для управления командами
 *
 */

public class CommandController {
    @Getter
    private static Map<String, Command> commands;
    private final History history;

    static {
        commands = new LinkedHashMap<>();

        commands.put("add", new Add());
        commands.put("add_if_max", new AddIfMax());
        commands.put("clear", new Clear());
        commands.put("execute_script", new ExecuteScript());
        commands.put("exit", new Exit());
        commands.put("filter_contains_name", new FilterContainsName());
        commands.put("filter_greater_than_minimal_point", new FilterGreaterThanMinimalPoint());
        commands.put("help", new Help());
        commands.put("history", new org.example.command.History());
        commands.put("info", new Info());
        commands.put("remove_by_id", new RemoveById());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("save", new Save());
        commands.put("show", new Show());
        commands.put("sum_of_average_point", new SumOfAveragePoint());
        commands.put("update", new Update());
    }


    public CommandController() {
        history = History.getInstance();
    }

    public void executeCommand(String commandName, String... args) {

        Command command = getCommandByName(commandName);
        command.execute(args);
        System.out.print("> ");
        history.add(command);
    }

    public boolean isValidCommand(String input) {
        return commands.get(input) != null;
    }

    public static Command getCommandByName(String commandName) {
        return commands.get(commandName);
    }

}
