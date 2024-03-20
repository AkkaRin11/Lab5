package org.example.command;

import org.example.controller.StreamController;
import org.example.controller.ConsoleController;
import org.example.model.*;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

public class Add extends Command{
    private final LabWorkService labWorkService;
    private final StreamController consoleController;

    public Add(){
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());
        consoleController = ConsoleController.getInstance();

        argSize = 0;
        name = "add";
        description = "Добавляет элемент в колекцию";
    }

    @Override
    public void execute(String... args) {

        if (!isSizeCorrect(args.length)){
            consoleController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        LabWork lb = consoleController.getLabWorkObj();

        labWorkService.add(lb);

        consoleController.print("Коллекция успешно добавлена");
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
