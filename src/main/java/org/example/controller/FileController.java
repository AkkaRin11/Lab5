package org.example.controller;

import org.example.dto.LabWorkDto;
import org.example.model.LabWork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;

import static org.example.util.Validation.checkDouble;
import static org.example.util.Validation.checkIntNumber;


/**
 *
 * Реализация StreamController для взаимодействия с файлами
 *
 */
public class FileController implements StreamController {
    private static FileController instance;
    private Scanner sc;
    private final ProgramStateController programStateController = ProgramStateController.getInstance();

    public static FileController getInstance() {
        if (instance == null) {
            instance = new FileController();
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
        return sc.nextLine();
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

    @Override
    public String readString(String name) {
        return readNextLine();
    }

    @Override
    public Long readLong(String name) {

        String line = readNextLine();

        if (checkIntNumber(line)) {
            return Long.parseLong(line);
        } else {
            return null;
        }
    }

    @Override
    public Long readLong(String name, String text) {
        String line = readNextLine();

        if (checkIntNumber(line)) {
            return Long.parseLong(line);
        } else {
            return null;
        }
    }

    @Override
    public <T extends Enum<T>> T readEnum(Class<T> enumClass) {

        T a = null;

        try {
            String line = readNextLine();

            if (checkIntNumber(line)) {
                int enumNumber = Integer.parseInt(line);

                if (enumNumber > 0 && enumNumber <= enumClass.getEnumConstants().length) {
                    a = enumClass.getEnumConstants()[enumNumber - 1];
                }

            } else {
                a = T.valueOf(enumClass, line.toUpperCase());
            }
        } catch (Exception e){
            a = enumClass.getEnumConstants()[0];
        }

        return a;
    }

    @Override
    public Double readDouble(String name) {

        String line = readNextLine().replaceAll(",", ".");

        if (checkDouble(line)) {
            return Double.parseDouble(line);
        } else {
            return null;
        }
    }

    @Override
    public Double readDouble(String name, String text) {
        String line = readNextLine().replaceAll(",", ".");

        if (checkDouble(line)) {
            return Double.parseDouble(line);
        } else {
            return null;
        }
    }

    @Override
    public void setScanner() {
        sc = programStateController.getScanner();
    }
}

