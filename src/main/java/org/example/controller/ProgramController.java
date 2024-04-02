package org.example.controller;

import java.io.FileNotFoundException;

public class ProgramController {
    private final CommandController commandController;
    private final StreamController consoleController;


    public ProgramController(){
        commandController = new CommandController();
        consoleController = ConsoleController.getInstance();

        consoleController.print("Программа запущена\nДля получения списка команд напишите: help");
    }

    public void run(){
        while (consoleController.hasNext()) {

            String line = consoleController.readNextLine();

            if (line.isEmpty()){
                continue;
            }

            String[] str = line.trim().split("\\s+");


            if (str.length == 0){
                continue;
            }

            if (!commandController.isValidCommand(str[0])){
                consoleController.print(str[0] + ": Имя " + str[0] +
                        "не распознано как имя командлета, функции, файла сценария или выполняемой программы\n" +
                        "Проверьте правильность написания имени, после чего повторите попытку.");
                continue;
            }

            String[] args = new String[str.length-1];
            System.arraycopy(str, 1, args, 0, str.length - 1);

            commandController.executeCommand(str[0], args);
        }

    }

    public void startSubroutine(String fileName){
        StreamController streamController;

        try {
            streamController = new FileController(fileName);
        } catch (FileNotFoundException e) {
            consoleController.print("Файла не существует");
            return;
        }

        while (streamController.hasNext()) {
            String line = streamController.readNextLine();

            if (line.isEmpty()){
                continue;
            }

            String[] str = line.split("\\s+");

            if (str.length == 0){
                continue;
            }

            if (!commandController.isValidCommand(str[0])){
                consoleController.print(str[0] + ": Имя " + str[0] +
                        "не распознано как имя командлета, функции, файла сценария или выполняемой программы\n" +
                        "Проверьте правильность написания имени, после чего повторите попытку.");
                continue;
            }

            String[] args = new String[str.length-1];
            System.arraycopy(str, 1, args, 0, str.length - 1);

            commandController.executeCommand(str[0], args);
        }
    }

}

