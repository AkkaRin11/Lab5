package org.example.controller;

import org.example.model.LabWork;

import java.util.LinkedHashSet;

/**
 *
 * Класс для взаимодейстия с потоками ввода-вывода
 *
 */

public interface StreamController {
    void print(String... str);

    boolean hasNext();

    String readNextLine();

    String readNextLine(String str);

    void printLabWorkObj(LabWork labWork);

    void printLabWorkObjs(LinkedHashSet<LabWork> labWork);

    String readString(String name);

    Long readLong(String name);

    Long readLong(String name, String text);

    <T extends Enum<T>> T readEnum(Class<T> enumClass);

    Double readDouble(String name);
    Double readDouble(String name, String text);

    void setScanner();

}
