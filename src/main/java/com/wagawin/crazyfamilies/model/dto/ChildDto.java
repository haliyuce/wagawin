package com.wagawin.crazyfamilies.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ChildDto {

    protected long id;

    protected String name;

    protected int age;

    private PersonDto parent;

    protected List<MealDto> favoriteMeals;
}
