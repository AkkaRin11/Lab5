package org.example.command;

import org.example.controller.StreamController;
import org.example.controller.ConsoleController;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

public class Save extends Command{
    private final LabWorkService labWorkService;
    private final StreamController consoleController;

    public Save(){
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());
        consoleController = ConsoleController.getInstance();

        argSize = 0;
        name = "save";
        description = "Сохраняет элементы колекции в заданный файл";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)){
            consoleController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        labWorkService.save();

        consoleController.print("Коллекция успешно сохранена");
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
