package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabWork {
    private Integer id; //Поле не может быть null, Значение поля должно быть большe 0, Значение этого поля должно быть уникальным
    private String name; //Поле не может быть null, Cтрoка нe может быть nyстой
    private Coordinates coordinates; //Полe может быть null
    private LocalDateTime creationDate; //Поле может null, Знaчение этого поля должно генерироваться автоматически
    private long minimalPoint; //Значение поля должно быть большe 0
    private Long averagePoint; //Поле может быть null, Знaчение поля должно быть больше 0
    private Difficulty difficulty; //Поле не может быть null
    private Person author; //Полe можeт быть null

}
