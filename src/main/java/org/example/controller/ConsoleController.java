package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.command.Command;

import java.util.ArrayList;
import java.util.Scanner;

@AllArgsConstructor
public class ConsoleController {
    private final CommandController cc;

    public void run(){

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (!cc.isValidCommand(str[0])){
                System.out.println(str[0] + ": Имя " + str[0] +
                        "не распознано как имя командлета, функции, файла сценария или выполняемой программы\n" +
                        "Проверьте правильность написания имени, после чего повторите попытку.");
                continue;
            }

            String[] args = new String[str.length-1];

            for (int i = 1 ; i < str.length; i++){
                args[i-1] = str[i];
            }

            cc.executeCommand(str[0], args);

        }

    }

}

