package org.example.controller;

import org.example.repository.LabWorkRepository;
import org.example.repository.LabWorkRepositoryImpl;
import org.example.util.NameUtil;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

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
        System.out.print("> ");
    }

    public void run() {
        Thread thread = new Thread(() -> {

            System.out.println("\nЗавершение работы по другой причине");
            try {
                LabWorkRepository labWorkRepository = LabWorkRepositoryImpl.getInstance(NameUtil.getInstance().getName());
                labWorkRepository.save();
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        thread.setDaemon(true);
        Runtime.getRuntime().addShutdownHook(thread);

        try {
            while (consoleController.hasNext()) {
                String line = consoleController.readNextLine();
                if (line == null) { // Check for EOF (Ctrl+D)
                    LabWorkRepository labWorkRepository = LabWorkRepositoryImpl.getInstance(NameUtil.getInstance().getName());
                    labWorkRepository.save();
                    System.exit(1);
                }

                if (line.isEmpty()) {
                    continue;
                }

                String[] str = line.trim().split("\\s+");
                if (str.length == 0) {
                    continue;
                }

                if (!CommandController.isValidCommand(str[0])) {
                    consoleController.print(str[0] + ": Имя " + str[0] +
                            " не распознано как имя командлета, функции, файла сценария или выполняемой программы\n" +
                            "Проверьте правильность написания имени, после чего повторите попытку.");
                    System.out.print("> ");
                    continue;
                }

                String[] args = new String[str.length - 1];
                System.arraycopy(str, 1, args, 0, str.length - 1);
                commandController.executeCommand(str[0], args);
            }
        } catch (Exception e) {
            consoleController.print("Произошла ошибка: " + e.getMessage());
            System.exit(1);
        }
    }
}