package org.example.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.example.model.*;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LabWorkRepositoryImpl implements LabWorkRepository {
    private static LabWorkRepositoryImpl instance;

    private final LinkedHashSet<LabWork> labWorks;

    private final Parser parser;

    public static LabWorkRepositoryImpl getInstance(String fileName){
        if (instance == null){
            instance = new LabWorkRepositoryImpl(fileName);
        }

        return instance;
    }

    private LabWorkRepositoryImpl(String fileName){
        parser = Parser.getInstance(fileName);
        labWorks = parser.read(fileName);
    }

    @Override
    public void add(LabWork lb) {
        labWorks.add(lb);
    }

    @Override
    public void clear() {
        labWorks.clear();
    }

    @Override
    public boolean removeById(int id) {
        LabWork lb = null;

        boolean flag = false;

        for (LabWork to: labWorks){
            if (to.getId() == id){
                lb = to;
                flag = true;
                break;
            }
        }
        
        labWorks.remove(lb);

        return flag;
    }

    @Override
    public void removeGreater(LabWork lb) {
        LinkedHashSet<LabWork> lh = new LinkedHashSet<>();

        for (LabWork to: labWorks){
            if (to.getMinimalPoint() > lb.getMinimalPoint()){
                lh.add(to);
            }
        }

        for (LabWork to: lh){
            labWorks.remove(to);
        }
    }

    @Override
    public void save() {
        parser.save(labWorks);
    }

    @Override
    public LinkedHashSet<LabWork> getCollection() {
        return labWorks;
    }

    @Override
    public void updateById(LabWork labW, int id) {
        LabWork lb = null;

        for (LabWork to: labWorks){
            if (to.getId() == id){
                lb = to;
                break;
            }
        }

        labWorks.remove(lb);
        labWorks.add(labW);
    }

    @Override
    public String getCollectionInfo() {
        return "LinkedHashSet, size: " + labWorks.size();
    }

}
