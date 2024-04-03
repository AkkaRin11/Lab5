package org.example.controller;

import org.example.command.Command;
import org.example.dto.*;
import org.example.model.LabWork;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class FileController implements StreamController {
    private static FileController instance;
    private final Scanner sc;

    private FileController(String fileName){
        try {
            sc = new Scanner(new File("src/main/java/org/example/data/" + fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static FileController getInstance(String fileName){
        if (instance == null){
            instance = new FileController(fileName);
        }

        return instance;
    }

    @Override
    public void print(String ... str){
        for(String to: str){
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
        for (LabWork to: labWork){
            printLabWorkObj(to);
        }

        if (labWork.isEmpty()){
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
}

