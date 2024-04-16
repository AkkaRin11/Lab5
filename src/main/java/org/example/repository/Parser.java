package org.example.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.example.model.LabWork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 *
 * Класс взаимодейстующий с файлом
 *
 */

public class Parser {
    private static Parser instance;

    private final String fileName;

    public static Parser getInstance(String fileName) {
        if (instance == null) {
            instance = new Parser(fileName);
        }

        return instance;
    }

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    public LinkedHashSet<LabWork> read(String fileName) {
        LinkedHashSet<LabWork> labWorks;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JSR310Module());

            String stringJson = "";

            Scanner scanner = new Scanner(new File(fileName));

            while (scanner.hasNextLine()) {
                stringJson += scanner.nextLine();
            }

            scanner.close();

            labWorks = mapper.readValue(stringJson, new TypeReference<LinkedHashSet<LabWork>>() {
            });


        } catch (FileNotFoundException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return labWorks;
    }


    public void save(LinkedHashSet<LabWork> labWorks) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());

        String jsonStr;

        try {
            jsonStr = mapper.writeValueAsString(labWorks);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(jsonStr);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
