package org.example.command;

import org.example.controller.ObjectController;
import org.example.model.LabWork;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

import static org.example.util.Validation.checkIntNumber;

/**
 *
 * Команда обновления элемента по id
 *
 */

public class Update extends Command {
    private final LabWorkService labWorkService;
    private final ObjectController objectController;

    public Update() {
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());
        objectController = new ObjectController();

        argSize = 1;
        name = "update";
        description = "Обновление элемента по id";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        if (checkIntNumber(args[0])) {

            int id = Integer.parseInt(args[0]);
            LabWork labWork = objectController.getLabWorkObj();

            boolean result = labWorkService.updateById(labWork, id);

            if (result) {
                objectController.print("Объект успешно обновлённ");
            } else {
                objectController.print("Элемента с таким id не существует");
            }

        } else {
            objectController.print("Введённый аргумент не является целым числом");
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
