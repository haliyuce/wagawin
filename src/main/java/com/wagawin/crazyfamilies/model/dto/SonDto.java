package com.wagawin.crazyfamilies.model.dto;

import lombok.Builder;

import java.util.List;

public class SonDto extends ChildDto {

    private String bicycleColor;

    @Builder
    SonDto(long id, String name, int age, PersonDto parent, List<MealDto> favoriteMeals, String bicycleColor) {
        super(id, name, age, parent, favoriteMeals);
        this.bicycleColor = bicycleColor;
    }
}
