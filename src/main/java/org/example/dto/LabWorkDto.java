package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.LabWork;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LabWorkDto {
    private Integer id;
    private String name;
    private CoordinatesDto coordinates;
    private LocalDateTime creationDate;
    private long minimalPoint;
    private Long averagePoint;
    private DifficultyDto difficulty;
    private PersonDto author;

    public static LabWorkDto toDto(LabWork labWork) {
        return new LabWorkDto(
                labWork.getId(),
                labWork.getName(),
                CoordinatesDto.toDto(labWork.getCoordinates()),
                labWork.getCreationDate(),
                labWork.getMinimalPoint(),
                labWork.getAveragePoint(),
                DifficultyDto.toDto(labWork.getDifficulty()),
                PersonDto.toDto(labWork.getAuthor())
        );

    }

    public static LabWork toDomainObject(LabWorkDto labWorkDto) {
        return new LabWork(
                labWorkDto.getId(),
                labWorkDto.getName(),
                CoordinatesDto.toDomainObject(labWorkDto.getCoordinates()),
                labWorkDto.getCreationDate(),
                labWorkDto.getMinimalPoint(),
                labWorkDto.getAveragePoint(),
                DifficultyDto.toDomainObject(labWorkDto.getDifficulty()),
                PersonDto.toDomainObject(labWorkDto.getAuthor())
        );
    }

    @Override
    public String toString() {
        return "\n" + "Id: " + getId().toString() +
                "\n" + "Name: " + getName()+
                "\n" + "Coordinates X: " + getCoordinates().getX().toString()+
                "\n" + "Coordinates Y: " + getCoordinates().getY().toString()+
                "\n" + "CreationDate: " + getCreationDate().toString()+
                "\n" + "MinimalPoint: " + getMinimalPoint()+
                "\n" + "AveragePoint: " + getAveragePoint().toString()+
                "\n" + "Difficulty: " + getDifficulty().name()+
                "\n" + "Author Name: " + getAuthor().getName()+
                "\n" + "Author Birthday: " + getAuthor().getBirthday().toString()+
                "\n" + "Author Height: " + getAuthor().getHeight()+
                "\n" + "Author HairColor: " + getAuthor().getHairColor().name()+
                "\n" + "Author Nationality: " + getAuthor().getNationality().name();
    }
}


