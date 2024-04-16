package org.example.command;

import org.example.controller.ObjectController;
import org.example.model.LabWork;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

/**
 *
 * Команда добавлющая элемент
 *
 */

public class Add extends Command {
    private final LabWorkService labWorkService;
    private final ObjectController objectController;

    public Add() {
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());
        objectController = new ObjectController();

        argSize = 0;
        name = "add";
        description = "Добавляет элемент в колекцию";
    }

    @Override
    public void execute(String... args) {

        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        LabWork lb = objectController.getLabWorkObj();

        labWorkService.add(lb);

        objectController.print("Коллекция успешно добавлена");
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
