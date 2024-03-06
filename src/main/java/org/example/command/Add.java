package org.example.command;

import org.example.model.*;
import org.example.service.LabWorkService;
import org.example.service.LabWorkServiceImpl;
import org.example.util.NameUtil;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

        LabWork lb = new LabWork((int) (Math.random() * Integer.MAX_VALUE), null, null, null, -1, null, null, null);

        Scanner sc = new Scanner(System.in);

        // name
        while (lb.getName() == null || lb.getName().isEmpty()) {
            System.out.print("name: ");

            String line = sc.nextLine();

            if (line.isEmpty()){
                System.out.println("name не может быть пустой строкой");
                continue;
            }

            lb.setName(line);
        }

        Coordinates coordinates = new Coordinates(null, null);


        // x
        while (coordinates.getX() == null) {
            System.out.print("x: ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (str.length != 1){
                System.out.println("Некорректное количество аргументов");
                continue;
            }

            if (str[0].matches("^[0-9]*[.][0-9]+$")){

                double a = 0;

                try {
                    a = Double.parseDouble(str[0]);
                } catch (Exception ignored){}

                if (a >= 365){
                    System.out.println("x может быть только целым числом меньше 365");
                    continue;
                }

                coordinates.setX(a);
            } else {
                System.out.println("x должен быть вещственным числом меньеше 365");
            }
        }



        // y
        while (coordinates.getY() == null) {
            System.out.print("y: ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (str.length != 1){
                System.out.println("Некорректное количество аргументов");
                continue;
            }

            if (str[0].matches("^[-+]?\\d+$")){

                long a = 0;

                try {
                    a = Long.parseLong(str[0]);
                } catch (Exception ignored){}

                if (a <= -592){
                    System.out.println("y может быть только целым числом больше -592");
                    continue;
                }

                coordinates.setY(a);
            } else {
                System.out.println("y может быть только целым числом больше -592");
            }
        }

        lb.setCoordinates(coordinates);


        // minimalPoint
        while (lb.getMinimalPoint() == -1) {
            System.out.print("minimal point: ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (str.length != 1){
                System.out.println("Некорректное количество аргументов");
                continue;
            }

            if (str[0].matches("^[-+]?\\d+$")){

                long a = 0;

                try {
                    a = Long.parseLong(str[0]);
                } catch (Exception ignored){}

                if (a <= 0){
                    System.out.println("minimal point может быть только целым числом больш 0");
                    continue;
                }

                lb.setMinimalPoint(a);
            } else {
                System.out.println("minimal point может быть только целым числом больш 0");
            }
        }


        // average point
        while (lb.getAveragePoint() == null) {
            System.out.print("average point point: ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (str.length != 1){
                System.out.println("Некорректное количество аргументов");
                continue;
            }

            if (str[0].matches("^[-+]?\\d+$")){

                long a = 0;

                try {
                    a = Long.parseLong(str[0]);
                } catch (Exception ignored){}

                if (a <= 0){
                    System.out.println("average point может быть только целым числом больш 0");
                    continue;
                }

                lb.setAveragePoint(a);
            } else {
                System.out.println("average point может быть только целым числом больш 0");
            }
        }


        // difficulty
        System.out.println("Введите одно из значений ниже");
        for (var to: Difficulty.values()){
            System.out.println("> " + to);
        }

        while (lb.getDifficulty() == null) {
            System.out.print("difficulty: ");

            String line = sc.nextLine();

            if (line.isEmpty()){
                System.out.println("difficulty не может быть пустой строкой");
                continue;
            }

            Difficulty a;

            try {
                a = Difficulty.valueOf(line);
            } catch (Exception e){
                System.out.println("Такого значения для difficulty не существует");
                continue;
            }

            lb.setDifficulty(a);
        }


        Person person = new Person(null, null, -1, null, null);

        //person.name
        while (person.getName() == null || person.getName().isEmpty()) {
            System.out.print("person.name: ");

            String line = sc.nextLine();

            if (line.isEmpty()){
                System.out.println("person.name не может быть пустой строкой");
                continue;
            }

            person.setName(line);
        }

        //person.date
        while (person.getBirthday() == null) {
            System.out.print("date(year month day): ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (str.length != 3){
                System.out.println("Некорректное количество аргументов, ожидается 3, получено " + str.length);
                continue;
            }

            int a = 0;
            if (str[0].matches("^[+]?\\d+$")){

                try {
                    a = Integer.parseInt(str[0]);
                } catch (Exception ignored){}

            } else {
                System.out.println("year может быть только положительным целым числом");
                continue;
            }

            int b = 0;
            if (str[0].matches("^[+]?\\d+$")){

                try {
                    a = Integer.parseInt(str[0]);
                } catch (Exception ignored){}

            } else {
                System.out.println("month может быть только положительным целым числом");
                continue;
            }

            int c = 0;
            if (str[0].matches("^[+]?\\d+$")){

                try {
                    a = Integer.parseInt(str[0]);
                } catch (Exception ignored){}

            } else {
                System.out.println("day может быть только положительным целым числом");
                continue;
            }

            Date date;

            try {
                Calendar calendar = new GregorianCalendar(a, b-1, c);
                date = calendar.getTime();
            } catch (Exception e){
                System.out.println("date не валидна");
                continue;
            }


            person.setBirthday(date);
        }

        System.out.println("Введите одно из значений ниже");
        for (var to: Color.values()){
            System.out.println("> " + to);
        }

        while (person.getHairColor() == null) {
            System.out.print("hair color: ");

            String line = sc.nextLine();

            if (line.isEmpty()){
                System.out.println("hair color не может быть пустой строкой");
                continue;
            }

            Color a;

            try {
                a = Color.valueOf(line);
            } catch (Exception e){
                System.out.println("Такого значения для hair color не существует");
                continue;
            }

            person.setHairColor(a);
        }


        System.out.println("Введите одно из значений ниже");
        for (var to: Country.values()){
            System.out.println("> " + to);
        }

        while (person.getNationality() == null) {
            System.out.print("nationality: ");

            String line = sc.nextLine();

            if (line.isEmpty()){
                System.out.println("nationality не может быть пустой строкой");
                continue;
            }

            Country a;

            try {
                a = Country.valueOf(line);
            } catch (Exception e){
                System.out.println("Такого значения для nationality не существует");
                continue;
            }

            person.setNationality(a);
        }

        lb.setAuthor(person);

        lb.setCreationDate(LocalDateTime.now());


        labWorkService.add(lb);

        sc.close();
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
