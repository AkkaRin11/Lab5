package org.example.command;

import org.example.controller.ObjectController;
import org.example.controller.StreamController;
import org.example.controller.ConsoleController;
import org.example.model.LabWork;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

public class SumOfAveragePoint extends Command{
    private final LabWorkService labWorkService;
    private final ObjectController objectController = new ObjectController();

    public SumOfAveragePoint(){
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());

        argSize = 0;
        name = "sum_of_average_point";
        description = "Вывести сумму всех полей averagePoint в коллекции";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)){
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }
        long sum = 0;

        var collection = labWorkService.getCollection();

        for(LabWork to: collection){
            sum += to.getAveragePoint();
        }

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
