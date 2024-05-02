package org.example.command;

import org.example.controller.ObjectController;
import org.example.model.LabWork;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

/**
 *
 * Команда удаляющая все элементы больше данного
 *
 */

public class RemoveGreater extends Command {
    private final LabWorkService labWorkService;
    private final ObjectController objectController;

    public RemoveGreater() {
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());
        objectController = new ObjectController();

        argSize = 0;
        name = "remove_greater";
        description = "Удаляет все элементы элементы из коллекции, перевыщающие заданный";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        LabWork labWork = objectController.getLabWorkObj();

        boolean flag = labWorkService.removeGreater(labWork);

        if (flag){
            objectController.print("Подходящие по условию объекты были удалены");
        } else {
            objectController.print("Подходящие по условию объекты не были найдены");
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
