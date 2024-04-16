package org.example.command;

import org.example.controller.ObjectController;
import org.example.model.LabWork;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

import java.util.LinkedHashSet;

/**
 *
 * Команда возвращающая все элементы содержащие данную строку в имени
 *
 */

public class FilterContainsName extends Command {
    private final LabWorkService labWorkService;
    private final ObjectController objectController = new ObjectController();

    public FilterContainsName() {
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());

        argSize = 1;
        name = "filter_contains_name";
        description = "Выводит элемены с вхождением подстроки в имени";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        LinkedHashSet<LabWork> filteredCollection = labWorkService.getCollectionByContainsName(args[0]);

        objectController.printLabWorkObjs(filteredCollection);
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
