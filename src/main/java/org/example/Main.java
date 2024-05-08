package org.example;

import org.example.command.Add;
import org.example.command.Command;
import org.example.controller.ProgramController;
import org.example.repository.LabWorkRepository;
import org.example.repository.LabWorkRepositoryImpl;
import org.example.util.NameUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NameUtil nameUtil = NameUtil.getInstance();

        String fileName = "";

        if (args.length != 1) {
            System.out.println("Введите название файла с колекцией аргументом командной строки");
            System.exit(1);
        }

        File file = null;

        try {
            file = new File(args[0]);
        } catch (Exception e) {
            System.out.println("Ошибка");
            System.exit(1);
        }

        nameUtil.setName(args[0]);


        ProgramController cn = new ProgramController();

        cn.run();

    }
}
