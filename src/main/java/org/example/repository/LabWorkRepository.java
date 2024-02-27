package org.example.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.example.model.*;

import java.io.*;
import java.security.spec.ECField;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

// взаимодействие с файлом
public class LabWorkRepository {
    private static LabWorkRepository instance;

    private LabWork labWork;


    private LabWorkRepository(String fileName){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JSR310Module());

            String stringJson = "";

            Scanner scanner = new Scanner(new File(fileName));

            while (scanner.hasNextLine()) {
                stringJson += scanner.nextLine();
            }

            scanner.close();

            labWork = mapper.readValue(stringJson, LabWork.class);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static LabWorkRepository getInstance(String fileName){
        if (instance == null){
            instance = new LabWorkRepository(fileName);
        }

        return instance;
    }

//    public void wright(){
//
//        try {
//
//            LabWork foo = new LabWork(1, "name", new Coordinates(1.0, 12L), LocalDateTime.now(), 13L, 11L,
//                    Difficulty.EASY, new Person("art", new Date(121212), 185L, Color.ORANGE, Country.JAPAN));
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.registerModule(new JSR310Module());
//
//            String jsonStr = mapper.writeValueAsString(foo);
//            //LabWork result = mapper.readValue(jsonStr, LabWork.class);
//            System.out.println(jsonStr);
//
//            LabWork result = mapper.readValue(jsonStr, LabWork.class);
//            System.out.println(result.getCreationDate());
//        } catch (Exception e){
//            System.out.println(e);
//        }
//    }

    /*

    методы для взаимодействия с данными

     */

}
