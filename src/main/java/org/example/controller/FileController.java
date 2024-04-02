package org.example.controller;

import org.example.command.Command;
import org.example.dto.*;
import org.example.model.LabWork;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class FileController implements StreamController {
    private final Scanner sc;

    public FileController(String fileName) throws FileNotFoundException {
        sc = new Scanner(new File(fileName));
    }

    @Override
    public void print(String... str) {

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
    public LabWork getLabWorkObj() {
        return null;
    }

    @Override
    public void printLabWorkObj(LabWork labWork) {

    }

    @Override
    public void printLabWorkObjs(LinkedHashSet<LabWork> labWork) {

    }

    @Override
    public void printHelp() {

    }
}

