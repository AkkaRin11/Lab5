package org.example.command;

import org.example.controller.StreamController;
import org.example.controller.ConsoleController;

public class Help extends Command{
    private final StreamController consoleController;

    public Help(){
        consoleController = ConsoleController.getInstance();

        argSize = 0;
        name = "help";
        description = "Выводит все доступные команды с описанием";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)){
            consoleController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        consoleController.printHelp();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
