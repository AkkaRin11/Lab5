package org.example.repository;

import org.example.model.LabWork;

import java.util.LinkedHashSet;
import java.util.Set;

public interface LabWorkRepository {
    void add(LabWork lb);
    void clear();
    boolean removeById(int id);
    void removeGreater(LabWork lb);
    void save();
    LinkedHashSet<LabWork> getCollection();
    void updateById(LabWork labW, int id);
    String getCollectionInfo();
}
