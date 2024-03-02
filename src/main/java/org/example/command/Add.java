package org.example.command;

import org.example.model.LabWork;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

import java.util.Scanner;

public class Add extends Command{
    private final LabWorkService labWorkService;
    private final NameUtil nameUtil = NameUtil.getInstance();

    public Add(){
        labWorkService = new LabWorkServiceImpl(nameUtil.getName());

        argSize = 0;
        name = "Add";
        description = "Добавляет элемент в колекцию";
    }

    @Override
    public void execute(String... args) {

        if (!isSizeCorr(args.length)){
            System.out.println("Неверное количество агркументов, ожидалось: " + argSize + ", получено: " + args.length);
            return;
        }

        LabWork lb = new LabWork(null, null, null, null, 0, null, null, null);

        Scanner sc = new Scanner(System.in);


        while (lb.getId() == null) {
            System.out.print("id: ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (str.length != 1){
                System.out.println("Некорректное количество аргументов");
                continue;
            }

            if (str[0].matches("[-+]?\\d+")){

                int a = 0;

                try {
                    a = Integer.parseInt(str[0]);
                } catch (Exception ignored){}

                lb.setId(a);
            } else {
                System.out.println("id может быть только целым знаковым числом");
                continue;
            }
        }

        // потом строчка удалиться
        System.out.println("Объект был добавлен");
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
