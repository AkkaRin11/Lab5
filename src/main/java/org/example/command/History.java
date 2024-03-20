package org.example.command;

import org.example.command_support.CommandHistory;
import org.example.controller.StreamController;
import org.example.controller.ConsoleController;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

public class History extends Command{
    private final LabWorkService labWorkService;
    private final StreamController consoleController;
    private final CommandHistory commandHistory;

    public History(){
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());
        consoleController = ConsoleController.getInstance();
        commandHistory = CommandHistory.getInstance();

        argSize = 0;
        name = "history";
        description = "Выводит историю команд";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)){
            consoleController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        var list = commandHistory.getCommandHistory();

        for(Command to: list){
            consoleController.print(to.name);
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
