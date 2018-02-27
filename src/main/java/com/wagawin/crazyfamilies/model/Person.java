package com.wagawin.crazyfamilies.model;

import com.wagawin.crazyfamilies.model.dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements DtoConverter<PersonDto>{

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private int age;

    @OneToOne(cascade = CascadeType.MERGE)
    private House house;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public PersonDto convertToDto() {
        return PersonDto.builder()
                        .age(this.age)
                        .house(house.convertToDto())
                        .name(name).id(id)
                        .children(this.children.stream().map(c -> c.convertToDto()).collect(Collectors.toList())).build();
    }
}
