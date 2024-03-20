package org.example.dto;

import org.example.model.Color;
import org.example.model.Country;
import org.example.model.Difficulty;

public enum ColorDto {
    BLACK,
    BLUE,
    ORANGE;

    public static ColorDto toDto(Color color){
        return ColorDto.valueOf(color.name());
    }

    public static Color toDomainObject(ColorDto colorDto){
        return Color.valueOf(colorDto.name());
    }
}
