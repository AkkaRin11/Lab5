package org.example.command;

import org.example.controller.ObjectController;
import org.example.controller.StreamController;
import org.example.controller.ConsoleController;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

public class Show extends Command{
    private final LabWorkService labWorkService;
    private final ObjectController objectController = new ObjectController();

    public Show(){
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());

        argSize = 0;
        name = "show";
        description = "Выводит все элементы коллекции";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)){
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        objectController.printLabWorkObjs(labWorkService.getCollection());
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
