package org.example.controller;


import org.example.util.NameUtil;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleController {
    private final CommandController cc;

    public ConsoleController(CommandController cc){
        this.cc = cc;


        System.out.println("Программа запущена\nДля получения списка команд напишите: help");
    }

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

