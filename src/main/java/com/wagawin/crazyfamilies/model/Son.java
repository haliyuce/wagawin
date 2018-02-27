package com.wagawin.crazyfamilies.model;

import com.wagawin.crazyfamilies.model.dto.ChildDto;
import com.wagawin.crazyfamilies.model.dto.SonDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Index;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value = "boy")
public class Son extends Child {

    @Column
    private String bicycleColor;

    @Builder
    public Son(long id, String name, int age, List<Meal> favoriteMeals, String bicycleColor, Person parent) {
        super(id, name, age, parent, favoriteMeals);
        this.bicycleColor = bicycleColor;
    }

    @Override
    public ChildDto convertToDto() {
        return SonDto.builder().id(super.id).age(super.age).name(super.name).bicycleColor(bicycleColor).build();
    }
}
