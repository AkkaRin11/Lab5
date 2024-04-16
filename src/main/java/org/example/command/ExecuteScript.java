package org.example.command;

import org.example.command_support.History;
import org.example.controller.CommandController;
import org.example.controller.ObjectController;
import org.example.controller.ProgramState;
import org.example.controller.ProgramStateController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * Команда воплоняющая скрипт по имени
 *
 */
public class ExecuteScript extends Command {
    private final ProgramStateController programStateController;
    private final ObjectController objectController;
    private final History history;

    public ExecuteScript() {
        objectController = new ObjectController();
        programStateController = ProgramStateController.getInstance();
        history = History.getInstance();

        argSize = 1;
        name = "execute_script";
        description = "Выполнить скрипт";
    }

    @Override
    public void execute(String... args) {

        if (!isSizeCorrect(args.length)) {
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        if (!programStateController.setFileName(args[0])) {
            objectController.print("Файл с таким именем не найден или у нему нету доступа");
        }

        if (history.isScriptWorking(args[0])) {
            return;
        } else {
            history.addScript(args[0]);
        }

        programStateController.setProgramState(ProgramState.ReadFromFile);

        Scanner sc;
        try {
            sc = new Scanner(new File("src/main/java/org/example/data/" +
                    programStateController.getFileName()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNext()) {
            String[] str = sc.nextLine().replaceAll("\n", "").split("\\s+");

            String[] ar = new String[str.length - 1];
            System.arraycopy(str, 1, ar, 0, str.length - 1);

            CommandController.getCommandByName(str[0]).execute(ar);
        }

        String scriptName = history.getPreviousScript();

        if (scriptName == null) {
            programStateController.setProgramState(ProgramState.ReadFromConsole);
        } else {
            programStateController.setFileName(scriptName);
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
