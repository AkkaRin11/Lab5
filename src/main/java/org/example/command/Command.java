package org.example.command;

import org.example.controller.CommandController;

public abstract class Command implements Describable, Executable{
    protected int argSize;

    public boolean isSizeCorr(int size){

        if (size != argSize){
            return false;
        }

        return true;
    }
}
