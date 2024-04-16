package org.example.command;

import org.example.controller.ObjectController;
import org.example.model.LabWork;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

import java.util.LinkedHashSet;

import static org.example.util.Validation.checkIntNumber;


/**
 *
 * Команда возвращающая все элементы коллекции у которых minimal_point больше заданного зачения
 *
 */
public class FilterGreaterThanMinimalPoint extends Command {
    private final LabWorkService labWorkService;
    private final ObjectController objectController = new ObjectController();

    public FilterGreaterThanMinimalPoint() {
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());

        argSize = 1;
        name = "filter_greater_than_minimal_point";
        description = "Выводит элемены со значением поля minimalPoint больше заданного";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        if (checkIntNumber(args[0])) {
            int minimalPoint = Integer.parseInt(args[0]);

            LinkedHashSet<LabWork> filteredCollection =
                    labWorkService.getCollectionByGreaterMinimalPoint(minimalPoint);

            objectController.printLabWorkObjs(filteredCollection);
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
