package org.example.command;

import org.example.controller.StreamController;
import org.example.controller.ConsoleController;
import org.example.model.LabWork;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

public class Update extends Command{
    private final LabWorkService labWorkService;
    private final StreamController consoleController;

    public Update(){
        NameUtil nameUtil = NameUtil.getInstance();
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());
        consoleController = ConsoleController.getInstance();

        argSize = 1;
        name = "update";
        description = "Обновление элемента по id";
    }

    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)){
            consoleController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        if (args[0].matches("^[-+]?\\d+$")){

            int id = Integer.parseInt(args[0]);

            var collection = labWorkService.getCollection();

            boolean flag = false;

            for(LabWork to: collection){
                if (to.getId() == id){
                    flag = true;
                    break;
                }
            }

            if (!flag){
                consoleController.print("Элемента с таким id не существует");
                return;
            }

            LabWork labWork = consoleController.getLabWorkObj();

            labWorkService.removeById(id);
            labWorkService.add(labWork);

            consoleController.print("Объект успешно обновлённ");

        } else {
            consoleController.print("Введённый аргумент не является целым числом");
            return;
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
