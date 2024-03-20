package org.example.controller;

import org.example.model.LabWork;

import java.util.LinkedHashSet;

public interface StreamController {
    void print(String ... str);

    boolean hasNext();

    String readNextLine();

    LabWork getLabWorkObj();

    void printLabWorkObj(LabWork labWork);
    void printLabWorkObjs(LinkedHashSet<LabWork> labWork);

    void printHelp();

}
