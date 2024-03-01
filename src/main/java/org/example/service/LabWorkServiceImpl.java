package org.example.service;

import org.example.model.LabWork;
import org.example.repository.LabWorkRepository;
import org.example.repository.LabWorkRepositoryImpl;

import java.util.LinkedHashSet;


public class LabWorkServiceImpl implements LabWorkService{
    private final LabWorkRepository labWorkRepository;

    public LabWorkServiceImpl(String fileName){
         labWorkRepository = LabWorkRepositoryImpl.getInstance(fileName);
    }

    @Override
    public void add(LabWork lb) {
        labWorkRepository.add(lb);
    }

    @Override
    public void clear() {
        labWorkRepository.clear();
    }

    @Override
    public void removeById(int id) {
        labWorkRepository.removeById(id);
    }

    @Override
    public void removeGreater(LabWork lb) {
        labWorkRepository.removeGreater(lb);
    }

    @Override
    public void save() {
        labWorkRepository.save();
    }

    @Override
    public LinkedHashSet<LabWork> show() {
        return labWorkRepository.show();
    }

    @Override
    public void updateById(LabWork labW, int id) {
        labWorkRepository.updateById(labW, id);
    }

}
