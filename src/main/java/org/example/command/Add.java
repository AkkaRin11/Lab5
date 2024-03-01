package org.example.command;

public class Add extends Command{


    @Override
    public void execute(String... args) {

        // проверка валидности количетсва переменных для команды

        isSizeCorr(12);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
