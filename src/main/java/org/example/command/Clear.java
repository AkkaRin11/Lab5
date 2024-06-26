package org.example.command;

import org.example.controller.ObjectController;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

/**
 *
 * Командадля отчистки коллекции
 *
 */

public class Clear extends Command {
    private final LabWorkService labWorkService;
    private final ObjectController objectController = new ObjectController();

    public Clear() {
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());

        argSize = 0;
        name = "clear";
        description = "Очищает колекцию";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        labWorkService.clear();

        objectController.print("Коллекция успешно очищена");
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
