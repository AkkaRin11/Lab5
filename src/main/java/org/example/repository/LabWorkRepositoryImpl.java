package org.example.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.example.model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LabWorkRepositoryImpl implements LabWorkRepository {
    private static LabWorkRepositoryImpl instance;

    private LinkedHashSet<LabWork> labWork;

    private String fileName;

    public static LabWorkRepositoryImpl getInstance(String fileName){
        if (instance == null){
            instance = new LabWorkRepositoryImpl(fileName);
        }

        return instance;
    }

    private LabWorkRepositoryImpl(String fileName){
        this.fileName = fileName;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JSR310Module());

            String stringJson = "";

            Scanner scanner = new Scanner(new File("src/main/java/org/example/data/lab5.json"
                    + fileName)); // тут ещё путь нужно норм указывать

            while (scanner.hasNextLine()) {
                stringJson += scanner.nextLine();
            }

            scanner.close();

            labWork = mapper.readValue(stringJson, LinkedHashSet.class);

        } catch (FileNotFoundException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(LabWork lb) {
        labWork.add(lb);
    }

    @Override
    public void clear() {
        labWork.clear();
    }

    @Override
    public void removeById(int id) {
        LabWork lb = null;

        for (LabWork to: labWork){
            if (to.getId() == id){
                lb = to;
                break;
            }
        }
        
        labWork.remove(lb);
    }

    @Override
    public void removeGreater(LabWork lb) {
        LinkedHashSet<LabWork> lh = new LinkedHashSet<>();

        for (LabWork to: labWork){
            if (to.getId() > lb.getId()){
                lh.add(to);
            }
        }

        for (LabWork to: lh){
            labWork.remove(to);
        }
    }

    @Override
    public void save() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());

        String jsonStr;

        try { // потом это отсюда убираем и протаскиваем исключение выше по слоям
            jsonStr = mapper.writeValueAsString(labWork);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        try {
            FileWriter writer = new FileWriter("src/main/java/org/example/data/lab5.json"
                    + fileName); // тут ещё путь нужно норм указывать
            writer.write(jsonStr);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public LinkedHashSet<LabWork> show() {
        return labWork;
    }

    @Override
    public void updateById(LabWork labW, int id) {
        LabWork lb = null;

        for (LabWork to: labWork){
            if (to.getId() == id){
                lb = to;
                break;
            }
        }

        labWork.remove(lb);
        labWork.add(labW);
    }

}
