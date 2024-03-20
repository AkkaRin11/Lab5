package org.example.command;

public abstract class Command implements Describable, Executable{
    protected int argSize;
    protected String name;
    protected String description;

    public boolean isSizeCorrect(int size){

        if (size != argSize){
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return getName() + " - " + getDescription();
    }
}
