package org.example.command;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.CommandController;

public abstract class Command implements Describable, Executable{
    protected int argSize;
    protected String name;
    protected String description;

    public boolean isSizeCorr(int size){

        if (size != argSize){
            return false;
        }

        return true;
    }
}
