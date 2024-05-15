package org.example.command;

import org.example.controller.ObjectController;
import org.example.model.LabWork;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

/**
 *
 * Команда добавляющая элемент если он наибольшй
 *
 */
public class AddIfMax extends Command {
    private final LabWorkService labWorkService;
    private final ObjectController objectController;

    public AddIfMax() {
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());
        objectController = new ObjectController();

        argSize = 0;
        name = "add_if_max";
        description = "Добавить элемент если он наибольший из всей колекции";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        LabWork labWork = objectController.getLabWorkObj();

        if (labWork == null){
            return;
        }

        boolean res = labWorkService.addIfMax(labWork);

        if (res) {
            objectController.print("Элемент наибольший, добавлен");
        } else {
            objectController.print("Элемент не наибольший, не добавлен");
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
