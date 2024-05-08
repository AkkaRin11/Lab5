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
    public String readNextLine(String str) {
        print(str);
        return sc.nextLine();
    }

    @Override
    public <T extends Enum<T>> T readEnum(Class<T> enumClass) {
        print("Введите одно из значений ниже");

        int it = 1;

        for (var to : enumClass.getEnumConstants()) {
            print("> " + it + " - " + to);
            it++;
        }

        T en = null;

        while (en == null) {
            print(enumClass.getSimpleName() + "(введите номер константы или её название): ");

            String line = readNextLine();

            if (line.isEmpty()) {
                print(enumClass.getSimpleName() + " не может быть пустой строкой");
                continue;
            }

            T a;

            try {

                if (checkIntNumber(line)){
                     int enumNumber = Integer.parseInt(line);

                     if (enumNumber > 0 && enumNumber <= enumClass.getEnumConstants().length){
                        a = enumClass.getEnumConstants()[enumNumber-1];
                     } else {
                         print("Такого значения для " + enumClass.getSimpleName() + " не существует");
                         continue;
                     }

                } else {
                    a = T.valueOf(enumClass, line.toUpperCase());
                }

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
            print(name + "(введите целое число): ");

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
    public long readLong(String name, String text) {
        Long res = null;

        while (res == null) {
            print(name + "(введите целое число, " + text + "): ");

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
            print(name + "(введиет вещественное число): ");

            String line = readNextLine().replaceAll(",", ".");
            String[] str = line.split("\\s+");

            if (str.length != 1) {
                print("Некорректное количество аргументов");
                continue;
            }

            if (checkDouble(str[0]) || checkIntNumber(str[0])) {

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
    public double readDouble(String name, String text) {
        Double res = null;

        while (res == null) {
            print(name + "(введите вещественное число, " + text + "): ");

            String line = readNextLine().replaceAll(",", ".");
            String[] str = line.split("\\s+");

            if (str.length != 1) {
                print("Некорректное количество аргументов");
                continue;
            }

            if (checkDouble(str[0]) || checkIntNumber(str[0])) {

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
            print(name + "(Введите строку): ");

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
        print(labWorkDto.toString());

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
