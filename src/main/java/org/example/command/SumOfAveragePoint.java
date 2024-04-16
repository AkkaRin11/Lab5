package org.example.command;

import org.example.controller.ObjectController;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

/**
 *
 * Команда возвращающая сумму average_point
 *
 */
public class SumOfAveragePoint extends Command {
    private final LabWorkService labWorkService;
    private final ObjectController objectController = new ObjectController();

    public SumOfAveragePoint() {
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());

        argSize = 0;
        name = "sum_of_average_point";
        description = "Вывести сумму всех полей averagePoint в коллекции";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }
        long sum = labWorkService.getSumOfAveragePoint();

        objectController.print("Сумма averagePoint в массиве: " + sum);
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
