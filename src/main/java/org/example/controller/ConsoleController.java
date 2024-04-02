package org.example.controller;

import org.example.command.Command;
import org.example.dto.*;
import org.example.model.LabWork;

import java.time.LocalDateTime;
import java.util.*;

public class ConsoleController implements StreamController {
    private static ConsoleController instance;
    private final Scanner sc;

    private ConsoleController(){
        sc = new Scanner(System.in);
    }
    public static ConsoleController getInstance(){
        if (instance == null){
            instance = new ConsoleController();
        }

        return instance;
    }

    public void print(String ... str){
        for(String to: str){
            System.out.print(to + " ");
        }
        System.out.println();
    }

    public boolean hasNext(){
        return sc.hasNext();
    }

    public String readNextLine(){
        return sc.nextLine();
    }

    @Override
    public LabWork getLabWorkObj() {
        LabWorkDto lb = new LabWorkDto((int) (Math.random() * Integer.MAX_VALUE), null, null, null, -1, null, null, null);

        // name
        while (lb.getName() == null || lb.getName().isEmpty()) {
            print("name: ");

            String line = sc.nextLine();

            if (line.isEmpty()){
                print("name не может быть пустой строкой");
                continue;
            }

            lb.setName(line);
        }

        CoordinatesDto coordinatesDto = new CoordinatesDto(null, null);


        // x
        while (coordinatesDto.getX() == null) {
            print("x: ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (str.length != 1){
                print("Некорректное количество аргументов");
                continue;
            }

            if (str[0].matches("^[0-9]*[.][0-9]+$")){

                double a = 0;

                try {
                    a = Double.parseDouble(str[0]);
                } catch (Exception ignored){}

                if (a >= 365){
                    print("x может быть только целым числом меньше 365");
                    continue;
                }

                coordinatesDto.setX(a);
            } else {
                print("x должен быть вещственным числом меньеше 365");
            }
        }



        // y
        while (coordinatesDto.getY() == null) {
            print("y: ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (str.length != 1){
                print("Некорректное количество аргументов");
                continue;
            }

            if (str[0].matches("^[-+]?\\d+$")){

                long a = 0;

                try {
                    a = Long.parseLong(str[0]);
                } catch (Exception ignored){}

                if (a <= -592){
                    print("y может быть только целым числом больше -592");
                    continue;
                }

                coordinatesDto.setY(a);
            } else {
                print("y может быть только целым числом больше -592");
            }
        }

        lb.setCoordinates(coordinatesDto);


        // minimalPoint
        while (lb.getMinimalPoint() == -1) {
            print("minimal point: ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (str.length != 1){
                print("Некорректное количество аргументов");
                continue;
            }

            if (str[0].matches("^[-+]?\\d+$")){

                long a = 0;

                try {
                    a = Long.parseLong(str[0]);
                } catch (Exception ignored){}

                if (a <= 0){
                    print("minimal point может быть только целым числом больш 0");
                    continue;
                }

                lb.setMinimalPoint(a);
            } else {
                print("minimal point может быть только целым числом больш 0");
            }
        }


        // average point
        while (lb.getAveragePoint() == null) {
            print("average point point: ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (str.length != 1){
                print("Некорректное количество аргументов");
                continue;
            }

            if (str[0].matches("^[-+]?\\d+$")){

                long a = 0;

                try {
                    a = Long.parseLong(str[0]);
                } catch (Exception ignored){}

                if (a <= 0){
                    print("average point может быть только целым числом больш 0");
                    continue;
                }

                lb.setAveragePoint(a);
            } else {
                print("average point может быть только целым числом больш 0");
            }
        }


        // difficulty
        print("Введите одно из значений ниже");
        for (var to: DifficultyDto.values()){
            print("> " + to);
        }

        while (lb.getDifficulty() == null) {
            print("difficulty: ");

            String line = sc.nextLine();

            if (line.isEmpty()){
                print("difficulty не может быть пустой строкой");
                continue;
            }

            DifficultyDto a;

            try {
                a = DifficultyDto.valueOf(line);
            } catch (Exception e){
                print("Такого значения для difficulty не существует");
                continue;
            }

            lb.setDifficulty(a);
        }


        PersonDto personDto = new PersonDto(null, null, -1, null, null);

        //person.name
        while (personDto.getName() == null || personDto.getName().isEmpty()) {
            print("person.name: ");

            String line = sc.nextLine();

            if (line.isEmpty()){
                print("person.name не может быть пустой строкой");
                continue;
            }

            personDto.setName(line);
        }

        //person.date
        while (personDto.getBirthday() == null) {
            print("date(year month day): ");

            String line = sc.nextLine();
            String[] str = line.split("\\s+");

            if (str.length != 3){
                print("Некорректное количество аргументов, ожидается 3, получено " + str.length);
                continue;
            }

            int a = 0;
            if (str[0].matches("^[+]?\\d+$")){

                try {
                    a = Integer.parseInt(str[0]);
                } catch (Exception ignored){}

            } else {
                print("year может быть только положительным целым числом");
                continue;
            }

            int b = 0;
            if (str[0].matches("^[+]?\\d+$")){

                try {
                    a = Integer.parseInt(str[0]);
                } catch (Exception ignored){}

            } else {
                print("month может быть только положительным целым числом");
                continue;
            }

            int c = 0;
            if (str[0].matches("^[+]?\\d+$")){

                try {
                    a = Integer.parseInt(str[0]);
                } catch (Exception ignored){}

            } else {
                print("day может быть только положительным целым числом");
                continue;
            }

            Date date;

            try {
                Calendar calendar = new GregorianCalendar(a, b-1, c);
                date = calendar.getTime();
            } catch (Exception e){
                print("date не валидна");
                continue;
            }


            personDto.setBirthday(date);
        }

        print("Введите одно из значений ниже");
        for (var to: ColorDto.values()){
            print("> " + to);
        }

        while (personDto.getHairColor() == null) {
            System.out.print("hair color: ");

            String line = sc.nextLine();

            if (line.isEmpty()){
                print("hair color не может быть пустой строкой");
                continue;
            }

            ColorDto a;

            try {
                a = ColorDto.valueOf(line);
            } catch (Exception e){
                print("Такого значения для hair color не существует");
                continue;
            }

            personDto.setHairColor(a);
        }


        print("Введите одно из значений ниже");
        for (var to: CountryDto.values()){
            print("> " + to);
        }

        while (personDto.getNationality() == null) {
            print("nationality: ");

            String line = sc.nextLine();

            if (line.isEmpty()){
                print("nationality не может быть пустой строкой");
                continue;
            }

            CountryDto a;

            try {
                a = CountryDto.valueOf(line);
            } catch (Exception e){
                print("Такого значения для nationality не существует");
                continue;
            }

            personDto.setNationality(a);
        }

        lb.setAuthor(personDto);

        lb.setCreationDate(LocalDateTime.now());

        return LabWorkDto.toDomainObject(lb);
    }

    @Override
    public void printLabWorkObj(LabWork labWork) {
        LabWorkDto labWorkDto = LabWorkDto.toDto(labWork);

        print(labWorkDto.getId().toString(), labWorkDto.getName(), labWorkDto.getCoordinates().getX().toString(),
                labWorkDto.getCoordinates().getY().toString(), labWorkDto.getCreationDate().toString(),
                "" + labWorkDto.getMinimalPoint(), labWorkDto.getAveragePoint().toString(),
                labWorkDto.getDifficulty().name(), labWorkDto.getAuthor().getName(),
                labWorkDto.getAuthor().getBirthday().toString(), "" + labWorkDto.getAuthor().getHeight(),
                labWorkDto.getAuthor().getHairColor().name(), labWorkDto.getAuthor().getNationality().name());

    }

    @Override
    public void printLabWorkObjs(LinkedHashSet<LabWork> labWork) {
        for (LabWork to: labWork){
            printLabWorkObj(to);
        }

        if (labWork.isEmpty()){
            print("Коллекция пуста");
        }
    }

    @Override
    public void printHelp() {
        CommandController commandController = new CommandController();
        for (Map.Entry<String, Command> entry : commandController.getCommands().entrySet()) {
            print(entry.getValue().toString());
        }
    }
}
