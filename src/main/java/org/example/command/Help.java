package org.example.command;

import org.example.controller.ObjectController;
import org.example.controller.StreamController;
import org.example.controller.ConsoleController;

public class Help extends Command{
    private final ObjectController objectController;

    public Help(){
        objectController = new ObjectController();

        argSize = 0;
        name = "help";
        description = "Выводит все доступные команды с описанием";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)){
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        objectController.printHelp();
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
