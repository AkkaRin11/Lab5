package org.example.dto;

import org.example.model.Color;
import org.example.model.Country;

public enum CountryDto {
    VATICAN,
    THAILAND,
    JAPAN;

    public static CountryDto toDto(Country country){
        return CountryDto.valueOf(country.name());
    }

    public static Country toDomainObject(CountryDto countryDto){
        return Country.valueOf(countryDto.name());
    }
}
