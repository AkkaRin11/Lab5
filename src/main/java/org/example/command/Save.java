package org.example.command;

import org.example.controller.ObjectController;
import org.example.controller.ProgramStateController;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

/**
 *
 * Команда для сохранения коллекции
 *
 */

public class Save extends Command {
    private final LabWorkService labWorkService;
    private final ObjectController objectController = new ObjectController();

    public Save() {
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());

        argSize = 0;
        name = "save";
        description = "Сохраняет элементы колекции в заданный файл";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        if (labWorkService.save()){
            if (ProgramStateController.getInstance().getIsFileDev()){
                objectController.print("Коллекция успешно сохранена в дефолтный файл");
            } else {
                objectController.print("Коллекция успешно сохранена");
            }
        } else {
            objectController.print("Файл не существует или к нему нету доступа, сохранение невозможно");
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
