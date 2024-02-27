package org.example.controller;

import org.example.command.*;
import org.example.command_support.CommandHistory;

import java.util.HashMap;
import java.util.Map;

public class CommandController {
    private final Map<String, Command> commands;
    private final CommandHistory commandHistory;
    private final String fileName;

    public CommandController() {
        commands = new HashMap<>();
        commandHistory = new CommandHistory();

        commands.put("add", new Add());
        commands.put("add_if_max", new AddIfMax());
        commands.put("clear", new Clear());
        commands.put("execute_script", new ExecuteScript());
        commands.put("exit", new Exit());
        commands.put("filter_contains_name", new FilterContainsName());
        commands.put("filter_greater_than_minimal_point", new FilterContainsName());
        commands.put("help", new Help());
        commands.put("history", new History());
        commands.put("info", new Info());
        commands.put("remove_by_id", new RemoveById());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("save", new Save());
        commands.put("show", new Show());
        commands.put("sum_of_average_point", new SumOfAveragePoint());
        commands.put("update", new Update());

        fileName = null; // считываем с клавы


//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String line = sc.next();
//            String[] tokens = line.split(" ");
//            Command command = commands.get(tokens[0]);
//            command.execute();
//        }


    }
}
