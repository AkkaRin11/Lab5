package org.example;

import org.example.controller.CommandController;
import org.example.controller.ConsoleController;
import org.example.model.Difficulty;
import org.example.repository.LabWorkRepository;
import org.example.repository.LabWorkRepositoryImpl;
import org.example.util.NameUtil;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NameUtil nameUtil = NameUtil.getInstance();

        String fileName = "";

        Scanner sc = new Scanner(System.in);

        while (Objects.equals(fileName, "")) {
            System.out.print("Введите имя файла с колекцией: ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            File file = null;

            try {
                file = new File("src/main/java/org/example/data/" + str[0]);
            } catch (Exception ignored){}


            if (file.exists()){
                fileName = str[0];
            } else {
                System.out.println("Файл не существет, проверьте правильность имени и попробуйте ещё");
            }

        }
        nameUtil.setName(fileName);


        CommandController cc = new CommandController();
        ConsoleController cn = new ConsoleController(cc);

        cn.run();

    }
}
