package org.example.controller;

import org.example.command.Command;
import org.example.model.LabWork;

import java.util.LinkedHashSet;

public interface StreamController {
    void print(String ... str);

    boolean hasNext();

    String readNextLine();

    void printLabWorkObj(LabWork labWork);
    void printLabWorkObjs(LinkedHashSet<LabWork> labWork);

    String readString(String name);

    long readLong(String name);

    <T extends Enum<T>> T readEnum(Class<T> enumClass);

    double readDouble(String name);

}
