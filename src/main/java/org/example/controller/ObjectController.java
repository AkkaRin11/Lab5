package org.example.controller;

import org.example.command.Command;
import org.example.dto.*;
import org.example.model.LabWork;

import java.time.LocalDateTime;
import java.util.*;

import static org.example.util.Validation.checkIntNumber;

/**
 *
 * Класс-прослойка между выводом объектов и командами
 *
 */

public class ObjectController {
    private StreamController streamController;
    private final ProgramStateController programStateController = ProgramStateController.getInstance();


    public LabWork getLabWorkObj() {
        setStream();

        LabWorkDto lb = new LabWorkDto((int) (Math.random() * Integer.MAX_VALUE), null, null, null, -1, null, null, null);

        lb.setName(streamController.readString("name"));

        CoordinatesDto coordinatesDto = new CoordinatesDto(null, null);

        while (coordinatesDto.getX() == null) {
            Double a = streamController.readDouble("x", "число должно быть меньеше 365");
            if (a == null){
                return null;
            }

            if (a < 365) {
                coordinatesDto.setX(a);
            } else {
                streamController.print("x должен быть вещственным числом меньеше 365");
            }
        }

        while (coordinatesDto.getY() == null) {
            Long a = streamController.readLong("y", "число должно быть больше -592");

            if (a == null){
                return null;
            }

            if (a > -592) {
                coordinatesDto.setY(a);
            } else {
                streamController.print("y может быть только целым числом больше -592");
            }
        }

        lb.setCoordinates(coordinatesDto);


        while (lb.getMinimalPoint() == -1) {
            Long a = streamController.readLong("minimal point", "число должно быть больше 0");

            if (a == null){
                return null;
            }

            if (a > 0) {
                lb.setMinimalPoint(a);
            } else {
                streamController.print("minimal point может быть только целым числом больше 0");
            }
        }


        while (lb.getAveragePoint() == null) {
            Long a = streamController.readLong("average point", "число должно быть больше 0");

            if (a == null){
                return null;
            }

            if (a > 0) {
                lb.setAveragePoint(a);
            } else {
                streamController.print("average point может быть только целым числом больше 0");
            }
        }


        lb.setDifficulty(streamController.readEnum(DifficultyDto.class));


        PersonDto personDto = new PersonDto(null, null, -1, null, null);


        personDto.setName(streamController.readString("name"));

        while (personDto.getBirthday() == null) {

            String line = streamController.readNextLine("date(year month day): ");
            String[] str = line.split("\\s+");

            if (str.length != 3) {
                streamController.print("Некорректное количество аргументов, ожидается 3, получено " + str.length);

                if (ProgramStateController.getInstance().getProgramState() == ProgramState.ReadFromFile){
                    return null;
                }

                continue;
            }

            Integer a = 0;
            if (checkIntNumber(str[0])) {

                try {
                    a = Integer.parseInt(str[0]);
                } catch (Exception ignored) {
                    return null;
                }

            } else {
                streamController.print("year может быть только положительным целым числом");
                continue;
            }

            Integer b = 0;
            if (checkIntNumber(str[0])) {

                try {
                    b = Integer.parseInt(str[0]);
                } catch (Exception ignored) {
                    return null;
                }

            } else {
                streamController.print("month может быть только положительным целым числом");
                continue;
            }

            Integer c = 0;
            if (checkIntNumber(str[0])) {

                try {
                    c = Integer.parseInt(str[0]);
                } catch (Exception ignored) {
                    return null;
                }

            } else {
                streamController.print("day может быть только положительным целым числом");
                continue;
            }

            Date date;

            try {
                Calendar calendar = new GregorianCalendar(a, b - 1, c);
                date = calendar.getTime();
            } catch (Exception e) {
                streamController.print("date не валидна");
                continue;
            }


            personDto.setBirthday(date);
        }

        personDto.setHairColor(streamController.readEnum(ColorDto.class));

        personDto.setNationality(streamController.readEnum(CountryDto.class));

        lb.setAuthor(personDto);

        lb.setCreationDate(LocalDateTime.now());

        return LabWorkDto.toDomainObject(lb);
    }

    public void printHelp() {
        setStream();
        for (Map.Entry<String, Command> entry : CommandController.getCommands().entrySet()) {
            streamController.print(entry.getValue().toString());
        }
    }

    public void print(String... str) {
        setStream();
        streamController.print(str);
    }

    public boolean hasNext() {
        setStream();
        return streamController.hasNext();
    }

    public void setStream() {
        if (programStateController.getProgramState() == ProgramState.ReadFromConsole) {
            streamController = ConsoleController.getInstance();
        } else {
            streamController = FileController.getInstance();
            streamController.setScanner();
        }
    }

    public String readString() {
        setStream();
        return streamController.readString("");
    }

    public void printLabWorkObj(LabWork labWork) {
        setStream();
        streamController.printLabWorkObj(labWork);

    }


    public void printLabWorkObjs(LinkedHashSet<LabWork> labWork) {
        setStream();
        streamController.printLabWorkObjs(labWork);
    }
}
