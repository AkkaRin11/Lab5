package org.example;

import org.example.controller.CommandController;
import org.example.controller.ConsoleController;
import org.example.repository.LabWorkRepository;
import org.example.repository.LabWorkRepositoryImpl;

public class Main {
    public static void main(String[] args) {

        CommandController cc = new CommandController();
        ConsoleController cn = new ConsoleController(cc);

        cn.run();

    }
}
