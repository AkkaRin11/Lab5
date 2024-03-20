package org.example.command;

import org.example.controller.StreamController;
import org.example.controller.ConsoleController;
import org.example.model.LabWork;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

public class FilterGreaterThanMinimalPoint extends Command{
    private final LabWorkService labWorkService;
    private final StreamController consoleController;

    public FilterGreaterThanMinimalPoint(){
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());
        consoleController = ConsoleController.getInstance();

        argSize = 1;
        name = "filter_greater_than_minimal_point";
        description = "Выводит элемены со значением поля minimalPoint больше заданного";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)){
            consoleController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        if (args[0].matches("^[-+]?\\d+$")){
            int minPoint = Integer.parseInt(args[0]);

            var collection = labWorkService.getCollection();

            for(LabWork to: collection){
                if (to.getMinimalPoint() > minPoint){
                    consoleController.printLabWorkObj(to);
                }
            }
        } else {
            consoleController.print("Введённый аргумент не является целым числом");
            return;
        }

        consoleController.print("Подходящие объекты были удалены");
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
