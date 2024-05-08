package org.example;

import org.example.command.Add;
import org.example.command.Command;
import org.example.controller.ProgramController;
import org.example.controller.ProgramStateController;
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

        String[] okr = args;

        if (okr.length != 1) {
            System.out.println("Путь до файла не указан или указан неверно, программа работает с дефолтным файлом");
            okr = new String[]{"dev"};
            ProgramStateController.getInstance().setIsFileDev(true);
        }

        File file = null;

        try {
            file = new File(okr[0]);
        } catch (Exception e) {
            System.out.println("Ошибка");
            System.exit(1);
        }

        nameUtil.setName(okr[0]);


        ProgramController cn = new ProgramController();

        cn.run();

    }
}
