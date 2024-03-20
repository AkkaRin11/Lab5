package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.LabWork;
import org.example.model.Person;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class PersonDto {
    private String name;
    private Date birthday;
    private long height;
    private ColorDto hairColor;
    private CountryDto nationality;

    public static PersonDto toDto(Person person){
        return new PersonDto(
                person.getName(),
                person.getBirthday(),
                person.getHeight(),
                ColorDto.toDto(person.getHairColor()),
                CountryDto.toDto(person.getNationality())
        );

    }

    public static Person toDomainObject(PersonDto personDto){
        return new Person(
                personDto.getName(),
                personDto.getBirthday(),
                personDto.getHeight(),
                ColorDto.toDomainObject(personDto.getHairColor()),
                CountryDto.toDomainObject((personDto.getNationality()))
        );
    }
}
