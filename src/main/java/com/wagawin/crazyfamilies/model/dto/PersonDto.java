package com.wagawin.crazyfamilies.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PersonDto {

    private long id;

    private String name;

    private int age;

    private HouseDto house;

    private List<ChildDto> children;

}
