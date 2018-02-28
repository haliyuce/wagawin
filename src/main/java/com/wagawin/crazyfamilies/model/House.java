package com.wagawin.crazyfamilies.model;

import com.wagawin.crazyfamilies.model.dto.HouseDto;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public class House implements DtoConverter<HouseDto>, Serializable {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = CascadeType.MERGE)
    private Person person;

    @Column
    private String address;

    @Column
    private String zipCode;

    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    @Override
    public HouseDto convertToDto() {
        return HouseDto.builder().id(id).address(address).houseType(houseType).zipCode(zipCode).person(person.convertToDto()).build();
    }

    public enum HouseType {
        FLAT, HOUSE, ESTATE
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        House house = (House) o;
        return id == house.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
