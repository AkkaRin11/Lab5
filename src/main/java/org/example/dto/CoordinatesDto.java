package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Coordinates;
import org.example.model.LabWork;
import org.example.model.Person;

@Setter
@Getter
@AllArgsConstructor
public class CoordinatesDto {
    private Double x;
    private Long y;

    public static CoordinatesDto toDto(Coordinates coordinates){
        return new CoordinatesDto(
                coordinates.getX(),
                coordinates.getY()
        );
    }

    public static Coordinates toDomainObject(CoordinatesDto coordinatesDto){
        return new Coordinates(
                coordinatesDto.getX(),
                coordinatesDto.getY()
        );
    }
}
