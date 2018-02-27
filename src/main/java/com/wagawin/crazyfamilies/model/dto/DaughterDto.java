package com.wagawin.crazyfamilies.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DaughterDto extends ChildDto {

    private String hairColor;

    @Builder
    DaughterDto(long id, String name, int age, PersonDto parent, List<MealDto> favoriteMeals, String hairColor) {
        super(id, name, age, parent, favoriteMeals);
        this.hairColor = hairColor;
    }
}
