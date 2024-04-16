package org.example.command;

import org.example.controller.ObjectController;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

/**
 *
 * Команда возвращающая информацию о коллекции
 *
 */
public class Info extends Command {
    private final LabWorkService labWorkService;
    private final ObjectController objectController = new ObjectController();

    public Info() {
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());

        argSize = 0;
        name = "info";
        description = "Выводит информацию о колекции";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        objectController.print(labWorkService.getCollectionInfo());
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
