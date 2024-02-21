package org.example.repository;

import org.example.model.LabWork;

// взаимодействие с файлом
public class LabWorkRepository {
    private static LabWorkRepository instance;

    private LabWorkRepository(){}

    public static LabWorkRepository getInstance(){
        if (instance == null){
            instance = new LabWorkRepository();
        }

        return instance;
    }

    /*

    методы для взаимодействия с данными

     */

}
