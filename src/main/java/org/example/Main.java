package org.example;

import org.example.controller.ProgramController;
import org.example.repository.LabWorkRepository;
import org.example.repository.LabWorkRepositoryImpl;
import org.example.util.NameUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            System.out.println("Ошибка ;(((");
            System.exit(1);
        }

        if (file.exists()) {
            fileName = args[0];
        } else {

            System.out.println("Файл не существует или к нему нету доступа, создаётся пустая коллекция");

            try {
                file.createNewFile();

                FileWriter writer = new FileWriter(args[0], false);
                writer.write("[]");
                writer.flush();

                fileName = args[0];
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        nameUtil.setName(fileName);


        ProgramController cn = new ProgramController();

        cn.run();

    }
}
