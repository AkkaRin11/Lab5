package org.example;

import org.example.command.Add;
import org.example.command.Command;
import org.example.controller.ProgramController;
import org.example.controller.ProgramStateController;
import org.example.model.Coordinates;
import org.example.model.LabWork;
import org.example.repository.LabWorkRepository;
import org.example.repository.LabWorkRepositoryImpl;
import org.example.util.NameUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        class Comp implements Comparator<Coordinates>{
            @Override
            public int compare(Coordinates o1, Coordinates o2) {
                if (o1.getX() < o2.getX()){
                    return -1;
                }
                if (o1.getX().equals(o2.getX())){
                    return 0;
                }
                return 1;
            }
        }


        ArrayList<Coordinates> coordinates = new ArrayList<>();

        coordinates.add(new Coordinates(1.2, 1L));
        coordinates.add(new Coordinates(1.1, 1L));

        coordinates.sort(new Comp());












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
