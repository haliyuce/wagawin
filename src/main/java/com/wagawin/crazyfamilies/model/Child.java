package com.wagawin.crazyfamilies.model;

import com.wagawin.crazyfamilies.model.dto.ChildDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {@Index(columnList = "bicycleColor"), @Index(columnList = "hairColor")})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name = "sex")
public abstract class Child implements DtoConverter<ChildDto> {

    @Id
    @GeneratedValue
    protected long id;

    @Column
    protected String name;

    @Column
    protected int age;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Person parent;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "child_favorite_meal",
            joinColumns = @JoinColumn(name = "child_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id", referencedColumnName = "id")
    )
    @OrderColumn(name="favorite_meal_order")
    protected List<Meal> favoriteMeals;

}
