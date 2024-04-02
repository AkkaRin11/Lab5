package org.example.command;

import org.example.controller.ConsoleController;
import org.example.controller.ProgramController;
import org.example.controller.StreamController;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

public class ExecuteScript extends Command{
    private final StreamController consoleController;

    public ExecuteScript(){
        consoleController = ConsoleController.getInstance();

        argSize = 0;
        name = "execute_script";
        description = "Добавить элемент если он наибольший из всей колекции";
    }
    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)){
            consoleController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }


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
