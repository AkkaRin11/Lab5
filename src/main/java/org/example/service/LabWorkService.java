package org.example.service;

import org.example.model.LabWork;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public interface LabWorkService {

    void add(LabWork lb);
    void clear();
    void removeById(int id);
    void removeGreater(LabWork lb);
    void save();
    LinkedHashSet<LabWork> show();
    void updateById(LabWork labW, int id);
}
