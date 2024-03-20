package org.example.service;

import org.example.model.LabWork;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public interface LabWorkService {

    void add(LabWork lb);
    void clear();
    boolean removeById(int id);
    void removeGreater(LabWork lb);
    void save();
    LinkedHashSet<LabWork> getCollection();
    void updateById(LabWork labW, int id);

    String getCollectionInfo();
}
