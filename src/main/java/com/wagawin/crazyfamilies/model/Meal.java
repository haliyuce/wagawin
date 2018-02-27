package com.wagawin.crazyfamilies.model;

import com.wagawin.crazyfamilies.model.dto.MealDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meal implements DtoConverter<MealDto> {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private Date invented;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        Meal meal = (Meal) o;
        return id == meal.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public MealDto convertToDto() {
        return MealDto.builder().id(id).invented(invented).name(name).build();
    }
}
