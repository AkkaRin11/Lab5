package org.example;

import org.example.controller.ProgramController;
import org.example.util.NameUtil;

import java.io.File;

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
            System.out.println("Файл не существет, проверьте правильность имени и попробуйте ещё");
            System.exit(1);
        }

        if (file.exists()) {
            fileName = args[0];
        } else {
            System.out.println("Файл не существет, проверьте правильность имени и попробуйте ещё");
            System.exit(1);
        }
        nameUtil.setName(fileName);


        ProgramController cn = new ProgramController();

        cn.run();

    }
}
