package org.example.command;

import org.example.controller.*;

public class ExecuteScript extends Command{
    private final ProgramStateController programStateController;
    private final ObjectController objectController;

    public ExecuteScript(){
        objectController = new ObjectController();
        programStateController = ProgramStateController.getInstance();

        argSize = 1;
        name = "execute_script";
        description = "Добавить элемент если он наибольший из всей колекции";
    }
    @Override
    public void execute(String... args) {
        if (!isSizeCorrect(args.length)){
            objectController.print("Неверное количество аргументов, ожидалось: " + argSize +
                    ", получено: " + args.length);
            return;
        }

        programStateController.setProgramState(ProgramState.ReadFromFile);
        programStateController.setFileName(args[0]);

        while (objectController.hasNext()){
            String[] str = objectController.readString().split("\\s+");

            String[] ar = new String[str.length-1];
            System.arraycopy(str, 1, ar, 0, str.length - 1);

            CommandController.getCommandByName(str[0]).execute(ar);
        }

        programStateController.setProgramState(ProgramState.ReadFromConsole);
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
