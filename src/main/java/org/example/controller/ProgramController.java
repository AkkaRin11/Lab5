package org.example.controller;

import org.example.repository.LabWorkRepository;
import org.example.repository.LabWorkRepositoryImpl;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;
import sun.misc.Signal;

/**
 *
 * Класс отвечающий за жизненный цикл программы
 *
 */

public class ProgramController {
    private final CommandController commandController;
    private final StreamController consoleController;


    public ProgramController() {
        commandController = new CommandController();
        consoleController = ConsoleController.getInstance();

        consoleController.print("Программа запущена\nДля получения списка команд напишите: help");
    }

    public void run() {
        try {

            while (consoleController.hasNext()) {


                String line = consoleController.readNextLine();

                if (line.isEmpty()) {
                    continue;
                }

                String[] str = line.trim().split("\\s+");


                if (str.length == 0) {
                    continue;
                }

                if (!commandController.isValidCommand(str[0])) {
                    consoleController.print(str[0] + ": Имя " + str[0] +
                            "не распознано как имя командлета, функции, файла сценария или выполняемой программы\n" +
                            "Проверьте правильность написания имени, после чего повторите попытку.");
                    continue;
                }

                String[] args = new String[str.length - 1];
                System.arraycopy(str, 1, args, 0, str.length - 1);

                commandController.executeCommand(str[0], args);
            }
        } catch (Exception e){
            LabWorkRepository labWorkRepository = LabWorkRepositoryImpl.getInstance(NameUtil.getInstance().getName());
            labWorkRepository.save();
        }

    }

}

