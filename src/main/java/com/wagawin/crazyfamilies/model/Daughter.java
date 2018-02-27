package com.wagawin.crazyfamilies.model;

import com.wagawin.crazyfamilies.model.dto.ChildDto;
import com.wagawin.crazyfamilies.model.dto.DaughterDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "girl")
public class Daughter extends Child {

    @Column()
    private String hairColor;

    @Builder
    public Daughter(long id, String name, int age, Person parent, List<Meal> favoriteMeals, String hairColor) {
        super(id, name, age, parent, favoriteMeals);
        this.hairColor = hairColor;
    }

    @Override
    public ChildDto convertToDto() {
        return DaughterDto.builder().id(super.id).age(super.age).name(super.name).hairColor(hairColor).build();
    }
}
