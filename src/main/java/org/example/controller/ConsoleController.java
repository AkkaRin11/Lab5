package org.example.controller;

import org.example.dto.LabWorkDto;
import org.example.model.LabWork;

import java.util.LinkedHashSet;
import java.util.Scanner;

import static org.example.util.Validation.checkDouble;
import static org.example.util.Validation.checkIntNumber;

/**
 *
 * Реализация StreamController для взаимодействия с консолью
 *
 */

public class ConsoleController implements StreamController {
    private static ConsoleController instance;
    private final Scanner sc;

    private ConsoleController() {
        sc = new Scanner(System.in);
    }

    public static ConsoleController getInstance() {
        if (instance == null) {
            instance = new ConsoleController();
        }

        return instance;
    }

    @Override
    public void print(String... str) {
        for (String to : str) {
            System.out.print(to + " ");
        }
        System.out.println();
    }

    @Override
    public boolean hasNext() {
        return sc.hasNext();
    }

    @Override
    public String readNextLine() {
        return sc.nextLine();
    }

    @Override
    public <T extends Enum<T>> T readEnum(Class<T> enumClass) {
        print("Введите одно из значений ниже");

        for (var to : enumClass.getEnumConstants()) {
            print("> " + to);
        }

        T en = null;

        while (en == null) {
            System.out.print(enumClass.getSimpleName() + ": ");

            String line = readNextLine();

            if (line.isEmpty()) {
                print(enumClass.getSimpleName() + " не может быть пустой строкой");
                continue;
            }

            T a;

            try {
                a = T.valueOf(enumClass, line);
            } catch (Exception e) {
                print("Такого значения для " + enumClass.getSimpleName() + " не существует");
                continue;
            }

            en = a;
        }

        return en;
    }

    @Override
    public long readLong(String name) {
        Long res = null;

        while (res == null) {
            print(name + ": ");

            String line = readNextLine();
            String[] str = line.split("\\s+");

            if (str.length != 1) {
                print("Некорректное количество аргументов");
                continue;
            }

            if (checkIntNumber(str[0])) {

                long a = 0;

                try {
                    a = Long.parseLong(str[0]);
                } catch (Exception ignored) {
                }

                res = a;
            } else {
                print(name + " может быть только целым числом");
            }
        }

        return res;
    }

    @Override
    public double readDouble(String name) {
        Double res = null;

        while (res == null) {
            print(name + ": ");

            String line = readNextLine();
            String[] str = line.split("\\s+");

            if (str.length != 1) {
                print("Некорректное количество аргументов");
                continue;
            }

            if (checkDouble(str[0])) {

                double a = 0;

                try {
                    a = Double.parseDouble(str[0]);
                } catch (Exception ignored) {
                }

                res = a;
            } else {
                print(name + " должен быть вещственным числом");
            }
        }

        return res;
    }

    @Override
    public void setScanner() {
    }

    @Override
    public String readString(String name) {
        String str = "";

        while (str.isEmpty()) {
            print(name + ": ");

            String line = readNextLine();

            if (line.isEmpty()) {
                print("Строка не может быть пустой");
                continue;
            }

            str = line;
        }

        return str;
    }

    @Override
    public void printLabWorkObj(LabWork labWork) {
        LabWorkDto labWorkDto = LabWorkDto.toDto(labWork);
        print("-------------------------------------------------------------------------------------");
        print(labWorkDto.getId().toString(), "|",
                labWorkDto.getName(), "|",
                labWorkDto.getCoordinates().getX().toString(), "|",
                labWorkDto.getCoordinates().getY().toString(), "|",
                labWorkDto.getCreationDate().toString(), "|",
                "" + labWorkDto.getMinimalPoint(), "|",
                labWorkDto.getAveragePoint().toString(), "|",
                labWorkDto.getDifficulty().name(), "|",
                labWorkDto.getAuthor().getName(), "|",
                labWorkDto.getAuthor().getBirthday().toString(), "|",
                "" + labWorkDto.getAuthor().getHeight(), "|",
                labWorkDto.getAuthor().getHairColor().name(), "|",
                labWorkDto.getAuthor().getNationality().name());

    }


    @Override
    public void printLabWorkObjs(LinkedHashSet<LabWork> labWork) {
        for (LabWork to : labWork) {
            printLabWorkObj(to);
        }

        if (labWork.isEmpty()) {
            print("Коллекция пуста");
        }
    }
}
