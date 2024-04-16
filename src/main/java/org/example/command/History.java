package org.example.command;

import org.example.controller.ObjectController;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

/**
 *
 * Команда возвращающая историю выполнения команд
 *
 */

public class History extends Command {
    private final org.example.command_support.History history;
    private final ObjectController objectController = new ObjectController();

    public History() {
        history = org.example.command_support.History.getInstance();

        argSize = 0;
        name = "history";
        description = "Выводит историю команд";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        var list = history.getCommandHistory();

        for (Command to : list) {
            objectController.print(to.name);
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
