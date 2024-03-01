package org.example.controller;

import org.example.command.*;
import org.example.command_support.CommandHistory;

import java.util.HashMap;
import java.util.Map;

public class CommandController {
    private final Map<String, Command> commands;
    private final CommandHistory commandHistory;

    public CommandController() {
        commands = new HashMap<>();
        commandHistory = new CommandHistory();

        commands.put("add", new Add());
        commands.put("add_if_max", new AddIfMax());
        commands.put("clear", new Clear());
        commands.put("execute_script", new ExecuteScript()); // а вот куда отправлять это вообще хз
        commands.put("exit", new Exit()); // выполняется на месте
        commands.put("filter_contains_name", new FilterContainsName());
        commands.put("filter_greater_than_minimal_point", new FilterContainsName());
        commands.put("help", new Help());
        commands.put("history", new History()); // не к сервису, либо история тоэе синглтон
        commands.put("info", new Info());
        commands.put("remove_by_id", new RemoveById());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("save", new Save());
        commands.put("show", new Show());
        commands.put("sum_of_average_point", new SumOfAveragePoint());
        commands.put("update", new Update());


    }

    public void executeCommand(String commandName, String... args) {
        Command command = commands.get(commandName);
        command.execute(args);
    }

    public boolean isValidCommand(String input){
        if (commands.get(input) != null){
            return true;
        }

        return false;
    }
}
