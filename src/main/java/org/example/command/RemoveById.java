package org.example.command;

import org.example.controller.ObjectController;
import org.example.controller.StreamController;
import org.example.controller.ConsoleController;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

public class RemoveById extends Command{
    private final LabWorkService labWorkService;
    private final ObjectController objectController = new ObjectController();

    public RemoveById(){
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());

        argSize = 1;
        name = "remove_by_id";
        description = "Удаляет элемент по id";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)){
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        boolean flag;

        if (args[0].matches("^[-+]?\\d+$")){
            flag = labWorkService.removeById(Integer.parseInt(args[0]));
        } else {
            objectController.print("Введённый аргумент не является целым числом");
            return;
        }

        if (flag){
            objectController.print("Элемент был успешно удалён");
        } else {
            objectController.print("Элемент не был удалён");
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
