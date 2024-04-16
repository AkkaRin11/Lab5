package org.example.dto;

import org.example.model.Difficulty;

public enum DifficultyDto {
    EASY,
    NORMAL,
    VERY_HARD,
    INSANE;

    public static DifficultyDto toDto(Difficulty difficulty) {
        return DifficultyDto.valueOf(difficulty.name());
    }

    public static Difficulty toDomainObject(DifficultyDto difficultyDto) {
        return Difficulty.valueOf(difficultyDto.name());
    }
}

