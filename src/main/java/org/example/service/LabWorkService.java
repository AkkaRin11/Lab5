package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.repository.LabWorkRepository;

// взаимодействие с взадимродействием с файлом
public class LabWorkService {
    private final LabWorkRepository labWorkRepository;

    public LabWorkService(String fileName){
         labWorkRepository = LabWorkRepository.getInstance(fileName);
    }

    /*

    методы для взаимодействия с данными

     */

}
