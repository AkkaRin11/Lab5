package org.example.controller;

import lombok.Getter;

import java.io.File;

/**
 *
 * Класс для отслеживания текущего состояния программы
 *
 */

public class ProgramStateController {

    private static ProgramStateController instance;
    @Getter
    private ProgramState programState;
    @Getter
    private String fileName = "";

    ProgramStateController() {
        programState = ProgramState.ReadFromConsole;
    }

    public static ProgramStateController getInstance() {
        if (instance == null) {
            instance = new ProgramStateController();
        }

        return instance;
    }

    public boolean setFileName(String fileName) {
        File file;

        try {
            file = new File("src/main/java/org/example/data/" + fileName);
        } catch (Exception e) {
            System.out.println("Файл не существет, проверьте правильность имени и попробуйте ещё");
            return false;
        }

        if (!file.exists()) {
            System.out.println("Файл не существет, проверьте правильность имени и попробуйте ещё");
            return false;
        }

        this.fileName = fileName;

        return true;
    }

    public void setProgramState(ProgramState programState) {
        this.programState = programState;
    }
}
