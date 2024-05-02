package org.example.controller;

import org.example.dto.LabWorkDto;
import org.example.model.LabWork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;


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
    public void printLabWorkObj(LabWork labWork) {
        LabWorkDto labWorkDto = LabWorkDto.toDto(labWork);

        print(labWorkDto.getId().toString(), labWorkDto.getName(), labWorkDto.getCoordinates().getX().toString(),
                labWorkDto.getCoordinates().getY().toString(), labWorkDto.getCreationDate().toString(),
                "" + labWorkDto.getMinimalPoint(), labWorkDto.getAveragePoint().toString(),
                labWorkDto.getDifficulty().name(), labWorkDto.getAuthor().getName(),
                labWorkDto.getAuthor().getBirthday().toString(), "" + labWorkDto.getAuthor().getHeight(),
                labWorkDto.getAuthor().getHairColor().name(), labWorkDto.getAuthor().getNationality().name());

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
    public long readLong(String name) {
        return Long.parseLong(readNextLine());
    }

    @Override
    public <T extends Enum<T>> T readEnum(Class<T> enumClass) {
        return T.valueOf(enumClass, readNextLine());
    }

    @Override
    public double readDouble(String name) {
        return Double.parseDouble(readNextLine());
    }

    @Override
    public void setScanner() {
        try {
            sc = new Scanner(new File(programStateController.getFileName()));
        } catch (FileNotFoundException e) {}
    }
}

