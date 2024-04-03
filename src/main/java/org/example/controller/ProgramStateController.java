package org.example.controller;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Scanner;
public class ProgramStateController {

    private static ProgramStateController instance;
    @Getter
    private ProgramState programState;
    @Getter
    @Setter
    private String FileName = "";

    ProgramStateController(){
        programState = ProgramState.ReadFromConsole;
    }
    public static ProgramStateController getInstance(){
        if (instance == null){
            instance = new ProgramStateController();
        }

        return instance;
    }

    public void setProgramState(ProgramState programState) {
        this.programState = programState;
    }
}
