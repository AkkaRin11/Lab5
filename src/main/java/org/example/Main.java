package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.controller.CommandController;
import org.example.controller.ConsoleController;
import org.example.repository.LabWorkRepository;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        CommandController cc = new CommandController();
        ConsoleController cn = new ConsoleController(cc);

        cn.run();

    }
}
